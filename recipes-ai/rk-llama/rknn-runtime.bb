SUMMARY = "Rockchip RKNN runtime (NPU user-space library)"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "(rk3588|rk3576|rockchip)"

SRC_URI = "https://github.com/airockchip/rknn-toolkit2/raw/refs/heads/master/rknpu2/runtime/Linux/librknn_api/aarch64/librknnrt.so"
SRC_URI[sha256sum] = "d31fc19c85b85f6091b2bd0f6af9d962d5264a4e410bfb536402ec92bac738e8"

S = "${UNPACKDIR}"

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${UNPACKDIR}/librknnrt.so ${D}${libdir}/librknnrt.so.1
    ln -sf librknnrt.so.1 ${D}${libdir}/librknnrt.so
}

INSANE_SKIP:${PN} += "already-stripped ldflags"

FILES:${PN} = "${libdir}/librknnrt.so.1"
FILES:${PN}-dev = "${libdir}/librknnrt.so"
