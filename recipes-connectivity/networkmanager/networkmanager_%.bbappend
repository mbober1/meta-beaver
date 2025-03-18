FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PACKAGECONFIG:append = " modemmanager wwan"

SRC_URI:append = " file://Aero2.nmconnection"

do_install:append() {
    install -d ${D}${sysconfdir}/NetworkManager/system-connections
    install -m 0600 ${WORKDIR}/Aero2.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections
}
