KERNEL_FEATURES += "features/wifi/wifi-pci.scc"
DISTRO_FEATURES:append = " wifi-pci"

IMAGE_INSTALL += " \
  pciutils \
  linux-firmware \
  iw \
  wpa-supplicant \
  rfkill \
  "
