KERNEL_FEATURES += " \
  features/usb/usb-gadgets.scc \
"

IMAGE_INSTALL += " \
  iw \
"

KERNEL_MODULE_AUTOLOAD += "g_ether"
DISTRO_FEATURES:append = " usbgadget"
MACHINE_FEATURES:append = " usbgadget"