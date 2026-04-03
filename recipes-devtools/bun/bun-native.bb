SUMMARY = "Bun JavaScript runtime (native)"
HOMEPAGE = "https://bun.sh"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

VERSION = "1.2.15"
SRC_URI = "https://github.com/oven-sh/bun/releases/download/bun-v${VERSION}/bun-linux-x64.zip;unzip=true"

SRC_URI[sha256sum] = "a261626367835bb3754a01ae07f884484ed17b0886b01e417b799591fa4d7901"

S = "${UNPACKDIR}"

inherit native

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/bun-linux-x64/bun ${D}${bindir}/bun
}
