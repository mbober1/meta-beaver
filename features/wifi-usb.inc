DISTRO_FEATURES:append = " wifi"
KERNEL_FEATURES += " \
  features/wifi/wifi-common.scc \
  features/wifi/wifi-usb.scc \
"

IMAGE_INSTALL += " \
  usbutils \
  linux-firmware \
  networkmanager \
  rfkill \
  "
