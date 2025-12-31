DESCRIPTION = "ePaper examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    git://github.com/waveshareteam/e-Paper;protocol=https;branch=master \
"

S = "${UNPACKDIR}/git/RaspberryPi_JetsonNano/c"
PV = "1.0.0+git"
SRCREV = "595d50b2cd37cd5770afc11e7d73794fed77785d"

DEPENDS += "lg"

PANEL_TYPE = "epd2in7V2"

EXTRA_OEMAKE += "INCLUDEDIR=${includedir}"
EXTRA_OEMAKE += "DESTDIR=${D}"
EXTRA_OEMAKE += "CC='${CC}'"
EXTRA_OEMAKE += "EPD=${PANEL_TYPE}"
EXTRA_OEMAKE += "RPI"

do_install() {
    install -d ${D}${bindir}
    install ${B}/${PN} ${D}${bindir}
}

FILES:${PN} = "${bindir}/${PN}"
