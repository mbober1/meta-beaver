DESCRIPTION = "Ollama alternative for Rockchip NPU"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

SRC_URI = " \
    git://github.com/NotPunchnox/rkllama;protocol=https;branch=main \
    file://fix-setuptools-requirements.patch \
"

PV = "0.0.4+git"
SRCREV = "45d76b35df829bafb98c2ed4ca57a7a02e516421"

inherit python_setuptools_build_meta

INSANE_SKIP:${PN} += "already-stripped ldflags file-rdeps"