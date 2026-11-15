SUMMARY = "GStreamer Rockchip MPP plugins"
DESCRIPTION = "GStreamer elements on top of librockchip_mpp: mpph264enc, \
mpph265enc, mppjpegenc and the MPP decoders (mppvideodec, mppjpegdec)."
HOMEPAGE = "https://github.com/JeffyCN/mirrors"
SECTION = "multimedia"

LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = " \
    git://github.com/JeffyCN/mirrors.git;protocol=https;branch=gstreamer-rockchip \
    file://0001-meson-define-gst_version_is_dev.patch \
"
SRCREV = "bff0659a51c13585b3c9c9a68aa8c435beeb3fe1"

COMPATIBLE_HOST = "aarch64.*-linux"

DEPENDS = " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    librockchip-mpp \
"

inherit meson pkgconfig

EXTRA_OEMESON = " \
    -Drkximage=disabled \
    -Dkmssrc=disabled \
    -Drga=disabled \
    -Dgpu=disabled \
    -Dvpxalphadec=disabled \
    --wrap-mode=nodownload \
"

FILES:${PN} += "${libdir}/gstreamer-1.0/libgstrockchipmpp.so"

RDEPENDS:${PN} += " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    librockchip-mpp \
"
