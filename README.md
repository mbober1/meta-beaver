Distro meta layer for Yocto Project
=========================================

## Software Features
- bluetooth
- can
- containers
- gps
- spi
- usb-gadgets
- wifi-pci
- wifi-usb
- wwan

You can enable feature adding ```include features/<feature_name>.inc``` to local.conf

## TODO
- add Hawkbit
- add RAUC (compare with SWUpdate)
- auto resize data partition
- add OTA fallback
- wireguard template via NetworkManager
- Squashfs?
- add Devicetree overlays support
- Overlayfs
- Initramfs

## Dependencies

  URI: <first dependency>
  branch: <branch name>

  URI: <second dependency>
  branch: <branch name>
