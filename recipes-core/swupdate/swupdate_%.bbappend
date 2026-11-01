FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS:append = " e2fsprogs libarchive zstd"
do_package_qa[noexec] = "1"

SRC_URI += "\
	file://swupdate.cfg \
	file://swupdate.default \
"

do_install:append () {
	rm -R ${D}${wwwdir}
	install -m 644 ${UNPACKDIR}/swupdate.cfg ${D}${sysconfdir}/
	install -d ${D}${sysconfdir}/default/
	install -m 644 ${UNPACKDIR}/swupdate.default ${D}${sysconfdir}/default/swupdate
}

RDEPENDS:${PN} += "galaktyczny-updater"
