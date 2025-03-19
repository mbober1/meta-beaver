KERNEL_FEATURES += " \
  features/wwan/wwan.scc \
"

IMAGE_INSTALL += " \
  rfkill \
  modemmanager \
  networkmanager \
  libqmi \
  "
