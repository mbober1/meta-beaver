DESCRIPTION = "Test image"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit core-image

IMAGE_LINGUAS = "en-us en-gb"
IMAGE_MACHINE_SUFFIX = ""

PACKAGE_CLASSES = "package_ipk"
INIT_MANAGER = "systemd"

QT_TOOLS = " \
  qtbase \
  qtbase-plugins \
"

FONTS = " \
  fontconfig \
  fontconfig-utils \
"

QT_DEV_TOOLS = " \
  qtbase-dev \
  qtbase-mkspecs \
  qtbase-tools \
"

KERNEL_EXTRA_INSTALL = " \
  kernel \
  kernel-devicetree \
  kernel-modules \
"

DEV_TOOLS = " \
  nano \
  htop \
  rsync \
  usbutils \
  pciutils \
  devmem2 \
  gdbserver \
"

IMAGE_INSTALL += " \
  ${QT_TOOLS} \
  ${FONTS} \
  ${KERNEL_EXTRA_INSTALL} \
  ${DEV_TOOLS} \
  packagegroup-core-boot \
  openssh \
  e2fsprogs \
  openssl \
  network-config-misc \
  util-linux \
  e2fsprogs \
  e2fsprogs-tune2fs \
  e2fsprogs-resize2fs \
  udev-extra-rules \
"

EXTRA_IMAGE_FEATURES = " \
  allow-empty-password \
  allow-root-login \
  empty-root-password \
  post-install-logging \
  tools-debug \
  ssh-server-openssh \
"
