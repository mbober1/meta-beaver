#
# RK3506 Cortex-M0 remoteproc / RPMsg support
#
# Enables the M0 remoteproc driver, the RPMsg mailboxes and the M0 firmware,
# and applies the mcu-rproc devicetree overlay to the base DTB.
#

KERNEL_FEATURES:append:luckfox-lyra-plus = " features/rproc/rproc.scc"
KERNEL_DT_OVERLAYS:append:luckfox-lyra-plus = " rk3506/rk3506g-luckfox-lyra-plus-mcu-rproc.dtso"

IMAGE_INSTALL:append:rk3506 = " \
  rk3506-rproc \
  rpmsg-bench \
  firmware-rk3506 \
"
