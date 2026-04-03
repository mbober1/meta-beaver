LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = " \
    git://github.com/Galaktyczne-Bambiki/galaktyczny-updater;protocol=https;branch=master \
    "

PV = "1.0.0+git"
SRCREV = "5c63b7f3680df25433912c74d1425baabc5254e0"

WWW_DIR ?= "/www"

DEPENDS += "nodejs-native"


do_compile[network] = "1"
do_compile () {
    cd ${S}
    rm -rf node_modules
    npm --loglevel info --proxy=${http_proxy} --https-proxy=${https_proxy} install
    npm run build ${EXTRA_OENPM}
}

do_install() {
    install -d ${D}/${WWW_DIR}

    for f in ${S}/dist/* ; do
        cp -r $f ${D}/${WWW_DIR}
    done
}

FILES:${PN} = "${WWW_DIR}/*"
