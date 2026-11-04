Distro meta layer for Yocto Project
=========================================

## Software Features
- can
- containers
- display
- gps
- ota
- rproc
- spi
- usb-gadgets
- wifi-pci
- wifi-usb
- wwan

You can enable feature adding ```include features/<feature_name>.inc``` to local.conf

## TODO
- add Hawkbit
- add RAUC (compare with SWUpdate)
- add OTA fallback
- wireguard template via NetworkManager
- Squashfs?
- add Devicetree overlays support
- Initramfs

## Dependencies

  Layer: OECORE
  URI: https://github.com/openembedded/openembedded-core.git

  Layer: BITBAKE
  URI: https://github.com/openembedded/bitbake.git

  Layer: CLANG
  URI: https://github.com/kraj/meta-clang

  Layer: SWUPDATE
  URI: https://github.com/sbabic/meta-swupdate

  Layer: VIRTUALIZATION
  URI: https://git.yoctoproject.org/meta-virtualization
