
# Automatically unlock modem
do_install:append(){
    install -d ${D}${sysconfdir}/ModemManager/fcc-unlock.d

    cd ${D}${datadir}/ModemManager/fcc-unlock.available.d/
    for f in * ; do
        ln -sft ${D}${sysconfdir}/ModemManager/fcc-unlock.d ${datadir}/ModemManager/fcc-unlock.available.d/$f
    done
}
