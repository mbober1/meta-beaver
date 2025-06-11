LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = " \
    git://github.com/Galaktyczne-Bambiki/galaktyczny-updater;protocol=https;branch=master \
    "

PV = "1.0.0+git"
SRCREV = "231efe0149cb9b7853c363b9ec22f4c606890a18"

S = "${WORKDIR}/git"
WWW_DIR ?= "/www"

DEPENDS += "bun-native"


do_compile() {
    bun install
    bun run build
}

do_install() {
    install -d ${D}/${WWW_DIR}

    for f in ${S}/dist/* ; do
        cp -r $f ${D}/${WWW_DIR}
    done
}

FILES:${PN} = "${WWW_DIR}/*"
