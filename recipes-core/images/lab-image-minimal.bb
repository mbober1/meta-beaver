DESCRIPTION = "Test image"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit core-image

IMAGE_LINGUAS = "en-us en-gb"
IMAGE_MACHINE_SUFFIX = ""

PACKAGE_CLASSES = "package_ipk"
INIT_MANAGER = "systemd"


KERNEL_EXTRA_INSTALL = " \
  kernel \
  kernel-devicetree \
  kernel-modules \
"

IMAGE_FEATURES:append = " \
  hwcodecs \
"

IMAGE_INSTALL += " \
  ${KERNEL_EXTRA_INSTALL} \
  packagegroup-core-boot \
	swupdate \
	swupdate-www \
	swupdate-client \
	swupdate-tools \
  nano \
  htop \
  openssh \
  e2fsprogs \
  openssl \
  rsync \
  libubootenv \
  network-config-misc \
  util-linux \
  e2fsprogs \
  e2fsprogs-tune2fs \
  e2fsprogs-resize2fs \
  udev-extra-rules \
"

EXTRA_IMAGE_FEATURES = " \
  debug-tweaks \
  tools-debug \
  ssh-server-openssh \
"

#Always add cmake to sdk
TOOLCHAIN_HOST_TASK:append = " nativesdk-cmake"
TOOLCHAIN_TARGET_TASK += " libgl-mesa-dev libegl-mesa-dev libgles1-mesa-dev libgles2-mesa-dev libgles3-mesa-dev"
