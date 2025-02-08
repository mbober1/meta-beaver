FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "\
	file://env.cfg \
	file://bootcmd.cfg \
	file://decompression.cfg \
"

SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"
