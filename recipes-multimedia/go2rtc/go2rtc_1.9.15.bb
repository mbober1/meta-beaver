SUMMARY = "go2rtc streaming server"
DESCRIPTION = "RTSP/RTMP/WebRTC streaming server"
HOMEPAGE = "https://github.com/AlexxIT/go2rtc"
SECTION = "multimedia"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=84f39ec0f1c900532a719dbb9ef7dfd3"
require ${BPN}-licenses.inc

SRC_URI = " \
    git://github.com/AlexxIT/go2rtc.git;protocol=https;branch=master;destsuffix=${GO_SRCURI_DESTSUFFIX} \
    file://go2rtc.service \
"
SRCREV = "7c05066c90ab6ad1fccb506d5f0408aa31ebe60f"
require ${BPN}-go-mods.inc

GO_IMPORT = "github.com/AlexxIT/go2rtc"
GO_INSTALL = "${GO_IMPORT}"
GO_LINKSHARED = ""

CGO_ENABLED = "0"
GOBUILDFLAGS:remove = "-buildmode=pie"

COMPATIBLE_HOST = "(x86_64|i.86|arm|aarch64).*-linux"

inherit go-mod go-mod-update-modules systemd

SYSTEMD_SERVICE:${PN} = "go2rtc.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/go2rtc.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += " \
    ${bindir}/go2rtc \
    ${systemd_system_unitdir}/go2rtc.service \
"
