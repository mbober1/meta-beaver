FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://beaver-kmeta;type=kmeta;name=beaver-kmeta;destsuffix=beaver-kmeta \
"

SRC_URI:append:radxa-zero-3e-custom = " \
	${@bb.utils.contains("DISTRO_FEATURES", "spi", "file://radxa-zero-3e-eink.patch", "", d)} \
"