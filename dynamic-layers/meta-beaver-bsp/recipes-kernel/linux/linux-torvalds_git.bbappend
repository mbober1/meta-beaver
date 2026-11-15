FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
	file://beaver-kmeta;type=kmeta;name=beaver-kmeta;destsuffix=beaver-kmeta \
"

SRC_URI:append:radxa-zero-3e-custom = " \
	${@bb.utils.contains("DISTRO_FEATURES", "spi", "file://radxa-zero-3e-eink.patch", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "camera", "file://radxa-zero-3e/media-bus-format-add-sensor-data.patch file://radxa-zero-3e/imx708-add-driver.patch file://radxa-zero-3e/dw9807-add-dw9817.patch file://radxa-zero-3e/radxa-zero-3e-imx708.dtso", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "mpp", "file://radxa-zero-3e/rockchip-iommu-export-enable.patch file://radxa-zero-3e/rockchip-pmu-export-idle-request.patch file://radxa-zero-3e/radxa-zero-3e-rkvenc-mpp.dtso", "", d)} \
"

KERNEL_FEATURES:rock-5b-plus += "features/npu/npu.scc"
