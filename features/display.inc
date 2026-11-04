KERNEL_FEATURES:append:luckfox-lyra-plus = " features/display/display-luckfox-lyra.scc"
KERNEL_DT_OVERLAYS:append:luckfox-lyra-plus = " rk3506/rk3506g-luckfox-lyra-plus-display.dtso"
UBOOT_EXTLINUX_KERNEL_ARGS:append:luckfox-lyra-plus = " fbcon=rotate:3"

IMAGE_INSTALL:append = " \
  psplash \
"
