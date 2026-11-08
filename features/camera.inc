DISTRO_FEATURES:append = " camera"

KERNEL_FEATURES:append:radxa-zero-3e-custom = " features/camera/camera.scc"
KERNEL_DT_OVERLAYS:append:radxa-zero-3e-custom = " radxa-zero-3e/radxa-zero-3e-imx708.dtso"
