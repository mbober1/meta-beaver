KERNEL_FEATURES += " \
  features/wifi/wifi-common.scc \
  features/wifi/wifi-realtek.scc \
"

DISTRO_FEATURES:append = " wifi"
UBOOT_EXTLINUX_KERNEL_ARGS += "cma=512mb"

IMAGE_INSTALL += " \
  pciutils \
  linux-firmware \
  networkmanager \
  rfkill \
  wireless-regdb \
  "
