# Vendor Rockchip MPP hardware encoder (VEPU540, H.264/HEVC) for the RK3566.

DISTRO_FEATURES:append = " mpp"

KERNEL_DT_OVERLAYS:append:radxa-zero-3e-custom = " radxa-zero-3e/radxa-zero-3e-rkvenc-mpp.dtso"
KERNEL_MODULE_AUTOLOAD += "rk_vcodec"

IMAGE_INSTALL:append = " \
  rockchip-mpp \
  librockchip-mpp \
  gstreamer1.0-plugins-rockchip \
  v4l-utils \
"
