DESCRIPTION = "Rust ePaper demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    git://github.com/mbober1/eink-demo;name=app;protocol=https;branch=master \
    git://github.com/caemor/epd-waveshare;name=epd-waveshare;protocol=https;branch=main;destsuffix=git/epd-waveshare \
"

PV = "1.0.0+git"
SRCREV_app = "529e54802bf42946de73a6c6270c659c2a1880a5"
SRCREV_epd-waveshare = "48458e195662eab97d10e852893295ee0876ba74"
SRCREV_FORMAT ?= "app_epd-waveshare"

DEPENDS += "libgpiod"

inherit cargo cargo-update-recipe-crates ptest-cargo

require ${BPN}-crates.inc
