DESCRIPTION = "Simple C++ program"
HOMEPAGE = "https://github.com/mbober1/yocto_cpp_helloworld"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

inherit meson

SRC_URI = "git://github.com/mbober1/yocto_cpp_helloworld.git;protocol=https;branch=master"

SRCREV = "02b9777bb8a5ae3e34787d5e88f4d2053f86dcf2"
S = "${WORKDIR}/git"
