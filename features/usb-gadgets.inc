KERNEL_FEATURES:append:luckfox-lyra-plus = " features/usb/usb-gadgets.scc"
KERNEL_FEATURES:append:stm32mp1 = " features/usb/usb-gadgets-stm32mp1.scc"

IMAGE_INSTALL += " \
  iw \
"

KERNEL_MODULE_AUTOLOAD += "g_ether"
DISTRO_FEATURES:append = " usbgadget"
MACHINE_FEATURES:append = " usbgadget"