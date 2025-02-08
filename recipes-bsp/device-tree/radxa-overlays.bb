FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit devicetree

SRC_URI = "	\
  file://radxa-zero3-rpi-camera-v2-overlay.dts \
  file://rock-5b-rpi-camera-v2.dts \
"

COMPATIBLE_MACHINE:aarch64 = "(.*)"
