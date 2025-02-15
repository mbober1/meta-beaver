KERNEL_FEATURES += "features/wifi/wifi-common.scc"
DISTRO_FEATURES:append = " wifi-pci"
UBOOT_EXTLINUX_KERNEL_ARGS += "cma=512mb"

IMAGE_INSTALL += " \
  pciutils \
  linux-firmware \
  iw \
  wpa-supplicant \
  rfkill \
  wireless-regdb \
  "
