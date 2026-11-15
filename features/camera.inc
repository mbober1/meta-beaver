DISTRO_FEATURES:append = " camera"

KERNEL_FEATURES:append:radxa-zero-3e-custom = " features/camera/camera.scc"
KERNEL_DT_OVERLAYS:append:radxa-zero-3e-custom = " radxa-zero-3e/radxa-zero-3e-imx708.dtso"

IMAGE_INSTALL:append = " \
  v4l-utils \
  gstreamer1.0 \
  gstreamer1.0-plugins-base-meta \
  gstreamer1.0-plugins-good-meta \
  gstreamer1.0-plugins-bad-meta \
  gstreamer1.0-plugins-ugly-meta \
  gstreamer1.0-rtsp-server-meta \
  go2rtc \
  camera-tools \
"

PACKAGECONFIG:append:pn-gstreamer1.0-plugins-ugly = " x264"
