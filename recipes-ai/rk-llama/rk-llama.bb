DESCRIPTION = "Llama.cpp with the Rockchip NPU integration as a GGML backend"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

SRC_URI = " \
    git://github.com/invisiofficial/rk-llama.cpp;protocol=https;branch=rknpu2 \
"

SRCREV = "d90f5fea7210020b427f9919c4b74f10e165a4fd"

inherit cmake pkgconfig

EXTRA_OECMAKE = " \
    -DLLAMA_RKNPU2=ON \
    -DLLAMA_BUILD_TESTS=OFF \
    -DLLAMA_TESTS_INSTALL=OFF \
    -DLLAMA_BUILD_EXAMPLES=OFF \
"

DEPENDS += "openssl"
RDEPENDS:${PN} += "rknn-runtime"
