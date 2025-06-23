FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS:append = " e2fsprogs libarchive zstd"
do_package_qa[noexec] = "1"

SRC_URI += "\
	file://swupdate.cfg \
	file://swupdate.default \
"

do_install:append () {
	if ${@bb.utils.contains('BAMBIK_SOURCES', '1', 'true', 'false', d)}; then
		rm -R ${D}${wwwdir}
	fi

	install -m 644 ${UNPACKDIR}/swupdate.cfg ${D}${sysconfdir}/
	install -d ${D}${sysconfdir}/default/
	install -m 644 ${UNPACKDIR}/swupdate.default ${D}${sysconfdir}/default/swupdate
}

RDEPENDS:${PN} += "${@bb.utils.contains('BAMBIK_SOURCES', '1', 'galaktyczny-updater', '', d)}"
