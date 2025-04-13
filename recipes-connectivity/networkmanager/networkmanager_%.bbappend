FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PACKAGECONFIG:append = " modemmanager wwan"

SRC_URI:append = " \
    file://Aero2.nmconnection \
"

#SRC_URI:append = " file://wifi_sta.nmconnection"

do_install:append() {
    install -d ${D}${sysconfdir}/NetworkManager/system-connections

    for CONFIG in ${WORKDIR}/*.nmconnection; do
        install -m 0600 ${CONFIG} ${D}${sysconfdir}/NetworkManager/system-connections
    done
}
