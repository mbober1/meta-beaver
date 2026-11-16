KERNEL_FEATURES:append:luckfox-lyra-plus = " features/rproc/rproc.scc"
KERNEL_DT_OVERLAYS:append:luckfox-lyra-plus = " rk3506/rk3506g-luckfox-lyra-plus-mcu-rproc.dtso"

KERNEL_FEATURES:append:stm32mp1 = " features/rproc/rproc-stm32mp1.scc"

IMAGE_INSTALL:append:rk3506 = " \
  rk3506-rproc \
  rpmsg-bench \
  firmware-rk3506 \
"

IMAGE_INSTALL:append:stm32mp1 = " \
  zephyr-rpmsg-client \
"
