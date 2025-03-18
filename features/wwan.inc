KERNEL_FEATURES += " \
  features/wifi/wifi-pci.scc \
"

IMAGE_INSTALL += " \
  rfkill \
  modemmanager \
  networkmanager \
  libqmi \
  "
