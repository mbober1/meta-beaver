DESCRIPTION = "Linux C libraries for manipulating GPIO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    git://github.com/joan2937/lg;protocol=https;branch=master \
"

S = "${UNPACKDIR}/git"
PV = "0.2.2+git"
SRCREV = "746f0df43774175090b93abcc860b6733eefc09b"

INSANE_SKIP:${PN} += "already-stripped"
EXTRA_OEMAKE += "CC='${CC}'"
EXTRA_OEMAKE += "CROSS_PREFIX=${TARGET_PREFIX}"

do_install() {
  oe_runmake install DESTDIR=${D}
  rm -rf ${D}/usr/local
}