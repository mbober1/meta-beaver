#!/bin/sh

PATH="/usr/sbin:/usr/bin:/sbin:/bin:${PATH}"

if [ $# -lt 1 ]; then
	exit 0
fi

error()
{
	echo "update.sh: $*" >&2
}

resolve_device()
{
	case "$1" in
		/dev/*)
			echo "$1"
			;;
		PARTLABEL=*|partlabel=*)
			findfs "PARTLABEL=${1#*=}" 2>/dev/null || \
				echo "/dev/disk/by-partlabel/${1#*=}"
			;;
		UUID=*|uuid=*)
			findfs "UUID=${1#*=}" 2>/dev/null || \
				echo "/dev/disk/by-uuid/${1#*=}"
			;;
		LABEL=*|label=*)
			findfs "LABEL=${1#*=}" 2>/dev/null || \
				echo "/dev/disk/by-label/${1#*=}"
			;;
		*)
			echo "$1"
			;;
	esac
}

get_current_root()
{
	CURRENT_ROOT=""

	for arg in $(cat /proc/cmdline); do
		case "$arg" in
			root=*)
				CURRENT_ROOT="${arg#root=}"
				;;
		esac
	done

	if [ -z "$CURRENT_ROOT" ]; then
		error "unable to determine root= from /proc/cmdline"
		return 1
	fi

	return 0
}

get_update_device()
{
	CURRENT_DEV="$(resolve_device "$CURRENT_ROOT")"

	if [ ! -b "$CURRENT_DEV" ]; then
		error "current root device '$CURRENT_DEV' is not a block device"
		return 1
	fi

	CURRENT_DEV="$(readlink -f "$CURRENT_DEV")"
	CURRENT_NAME="$(basename "$CURRENT_DEV")"
	PART_NO="$(cat "/sys/class/block/${CURRENT_NAME}/partition" 2>/dev/null)"

	if [ -z "$PART_NO" ]; then
		error "unable to determine partition number of $CURRENT_DEV"
		return 1
	fi

	DISK_NAME="$(basename "$(readlink -f "/sys/class/block/${CURRENT_NAME}/..")")"
	DISK="/dev/${DISK_NAME}"

	# Locate both slots by partition name instead of assuming fixed
	# partition numbers, so the layout can change without touching this
	# script.
	SLOT_A="$(findfs PARTLABEL=rootfsA 2>/dev/null)"
	SLOT_B="$(findfs PARTLABEL=rootfsB 2>/dev/null)"

	if [ ! -b "$SLOT_A" ] || [ ! -b "$SLOT_B" ]; then
		error "rootfsA/rootfsB partitions not found on this device"
		error "reinstall with the A/B image (ota_partitions.wks.in)"
		return 1
	fi

	SLOT_A="$(readlink -f "$SLOT_A")"
	SLOT_B="$(readlink -f "$SLOT_B")"

	if [ "$CURRENT_DEV" = "$SLOT_A" ]; then
		UPDATE_ROOT="$SLOT_B"
		UPDATE_LABEL="rootfsB"
	else
		UPDATE_ROOT="$SLOT_A"
		UPDATE_LABEL="rootfsA"
	fi

	UPDATE_NAME="$(basename "$UPDATE_ROOT")"
	UPDATE_PART="$(cat "/sys/class/block/${UPDATE_NAME}/partition" 2>/dev/null)"

	if [ -z "$UPDATE_PART" ]; then
		error "unable to determine partition number of $UPDATE_ROOT"
		return 1
	fi

	if [ ! -b "$UPDATE_ROOT" ]; then
		error "expected update partition $UPDATE_ROOT does not exist"
		return 1
	fi

	return 0
}

format_update_device()
{
	umount "$UPDATE_ROOT" 2>/dev/null || true
	mkfs.ext4 -q -F -m0 -L "$UPDATE_LABEL" "$UPDATE_ROOT"
}

set_active_partition()
{
	if ! command -v sfdisk >/dev/null 2>&1; then
		error "sfdisk not found, cannot switch the boot slot"
		return 1
	fi

	if ! sfdisk --part-attrs "$DISK" "$PART_NO" "" >/dev/null; then
		error "failed to clear the bootable attribute on $DISK p$PART_NO"
		return 1
	fi

	if ! sfdisk --part-attrs "$DISK" "$UPDATE_PART" LegacyBIOSBootable >/dev/null; then
		error "failed to mark $UPDATE_ROOT as bootable"
		return 1
	fi

	if ! sfdisk --part-attrs "$DISK" "$UPDATE_PART" | grep -q LegacyBIOSBootable; then
		error "$UPDATE_ROOT is not marked bootable after the update"
		return 1
	fi

	sync
	return 0
}

case "$1" in
	preinst)
		get_current_root || exit 1
		get_update_device || exit 1
		format_update_device || exit 1
		ln -sfn "$UPDATE_ROOT" /dev/update
		;;
	postinst)
		get_current_root || exit 1
		get_update_device || exit 1
		set_active_partition || exit 1
		;;
esac

exit 0
