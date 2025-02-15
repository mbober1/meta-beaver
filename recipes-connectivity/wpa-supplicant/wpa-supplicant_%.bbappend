FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://wpa_supplicant-wl.conf"


do_install:append () {
	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 600 ${WORKDIR}/wpa_supplicant-wl.conf ${D}${sysconfdir}/wpa_supplicant.conf
}

SYSTEMD_SERVICE:${PN} = "wpa_supplicant@wlp1s0.service"
SYSTEMD_AUTO_ENABLE = "disable"
