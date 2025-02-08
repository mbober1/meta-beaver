#!/bin/sh

if [ $# -lt 1 ]; then
	exit 0;
fi

FIRST_PART="3"
SECOND_PART="4"

function get_current_root_device
{
	for i in `cat /proc/cmdline`; do
		if [ ${i:0:5} = "root=" ]; then
			CURRENT_ROOT="${i:5}"
		fi
	done

	# CURRENT_ROOT == /dev/mmcblkXpY
}

function get_update_part
{
	CURRENT_PART="${CURRENT_ROOT: -1}"
	if [ $CURRENT_PART = $FIRST_PART ]; then
		UPDATE_PART=$SECOND_PART;
	else
		UPDATE_PART=$FIRST_PART;
	fi
}

function get_update_device
{
	UPDATE_ROOT=${CURRENT_ROOT%?}${UPDATE_PART}
}

function format_update_device
{
	umount $UPDATE_ROOT || true
	mkfs.ext4 $UPDATE_ROOT -q -F -m0 -L rootfs${UPDATE_PART}
}

if [ $1 == "preinst" ]; then
	#stop any services here

	# get the current root device
	get_current_root_device

	# get the device to be updated
	get_update_part
	get_update_device

	# format the device to be updated
	format_update_device

	# create a symlink for the update process
	ln -sf $UPDATE_ROOT /dev/update
fi

if [ $1 == "postinst" ]; then

	# change bootable flag
	((echo "x"; echo "A"; echo $FIRST_PART; echo "A"; echo $SECOND_PART; echo "r"; echo "w") | fdisk /dev/mmcblk0)

fi
