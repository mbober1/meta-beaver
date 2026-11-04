# OTA A/B update support

DISTRO_FEATURES:append = " ota"
MACHINE_FEATURES:append = " ota"

IMAGE_INSTALL:append = " \
  swupdate \
  swupdate-www \
  swupdate-client \
  swupdate-tools \
  libubootenv \
"

IMAGE_INSTALL:append:rockchip = " overlayfs-data"
WIC_CREATE_EXTRA_ARGS:append = " --no-fstab-update"
KERNEL_FEATURES:append = " features/overlayfs/overlayfs.scc"
