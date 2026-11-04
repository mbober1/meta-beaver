SUMMARY = "Persistent overlayfs mounts backed by the /data partition"
DESCRIPTION = "Uses the persistent data partition as the overlayfs upper layer so \
selected writable paths keep their contents across A/B rootfs updates."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit overlayfs systemd

SRC_URI = " \
    file://overlayfs-data.conf \
    file://grow-data-partition \
    file://data-grow.service \
    file://data.mount \
"
S = "${UNPACKDIR}"

OVERLAYFS_WRITABLE_PATHS[data] = "/home /root /srv"

RDEPENDS:${PN} += " \
    util-linux-sfdisk \
    util-linux-partx \
    util-linux-findfs \
    util-linux-lsblk \
    util-linux-blkid \
    e2fsprogs-e2fsck \
    e2fsprogs-resize2fs \
"

SYSTEMD_SERVICE:${PN}:append = " data-grow.service data.mount"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}/data

    install -d ${D}${sysconfdir}/tmpfiles.d
    install -m 0644 ${UNPACKDIR}/overlayfs-data.conf ${D}${sysconfdir}/tmpfiles.d/overlayfs-data.conf

    install -d ${D}${sbindir}
    install -m 0755 ${UNPACKDIR}/grow-data-partition ${D}${sbindir}/grow-data-partition

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/data-grow.service ${D}${systemd_system_unitdir}/
    install -m 0644 ${UNPACKDIR}/data.mount ${D}${systemd_system_unitdir}/
}

FILES:${PN} += " \
    /data \
    ${sysconfdir}/tmpfiles.d/overlayfs-data.conf \
    ${sbindir}/grow-data-partition \
    ${systemd_system_unitdir}/data-grow.service \
    ${systemd_system_unitdir}/data.mount \
"
