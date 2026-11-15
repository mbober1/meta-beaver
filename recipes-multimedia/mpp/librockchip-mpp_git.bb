SUMMARY = "Rockchip Media Process Platform (MPP) library"
DESCRIPTION = "Userspace MPP library and headers providing hardware H.264, \
HEVC, VP8 and JPEG encode/decode through the Rockchip vendor kernel driver \
(/dev/mpp_service)."
HOMEPAGE = "https://github.com/rockchip-linux/mpp"
SECTION = "libs/multimedia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSES/Apache-2.0;md5=7f43e699e0a26fae98c2938092f008d2"

SRC_URI = "git://github.com/rockchip-linux/mpp;protocol=https;nobranch=1"
SRCREV = "9b56348cc61393c84662e6d6dd78feae67773a36"

PV = "1.0+git"

COMPATIBLE_HOST = "aarch64.*-linux"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_TEST=OFF \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"

FILES:${PN} += "${libdir}/librockchip_mpp.so.*"
FILES:${PN}-staticdev += "${libdir}/librockchip_mpp.a"
