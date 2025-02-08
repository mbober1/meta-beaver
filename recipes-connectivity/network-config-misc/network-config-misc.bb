SUMMARY = "Network configuration files"
DESCRIPTION = "Network configuration files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} = "systemd systemd-networkd dhcp-client"

SRC_URI += "\
	file://20-usb.network \
	file://25-wireless.network \
	file://30-end0.network \
	file://80-can.network \
"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d 0755 ${D}/etc/systemd/network
	install -m 0644 ${WORKDIR}/*.network ${D}/etc/systemd/network/
}

FILES_${PN} = "/etc/systemd/network"
