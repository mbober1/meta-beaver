FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://beaver-kmeta;type=kmeta;name=beaver-kmeta;destsuffix=beaver-kmeta \
	file://nvme.cfg \
"
