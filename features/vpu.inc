# Hardware video encode on the RK3566 (mainline Hantro VEPU121, JPEG only).

DISTRO_FEATURES:append = " vpu"

KERNEL_FEATURES:append:radxa-zero-3e-custom = " features/vpu/vpu.scc"

IMAGE_INSTALL:append = " \
  v4l-utils \
"
