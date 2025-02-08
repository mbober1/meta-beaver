DESCRIPTION = "Extra udev rules"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=b97a012949927931feb7793eee5ed924"

SRC_URI = " \
       file://serial.rules \
"

do_install() {
	install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/serial.rules ${D}${sysconfdir}/udev/rules.d/serial.rules
}

FILES_${PN} = "${sysconfdir}/udev"
RDEPENDS_${PN} = "udev"