## Prepare toolchain

TOOLCHAIN="arm-gnu-toolchain-14.2.rel1-x86_64-arm-none-linux-gnueabihf"
wget https://developer.arm.com/-/media/Files/downloads/gnu/14.2.rel1/binrel/$TOOLCHAIN.tar.xz
tar xf $TOOLCHAIN.tar.xz
rm $TOOLCHAIN.tar.xz
PATH="$(readlink -f $TOOLCHAIN/bin)":$PATH


## Basic boot chain
### Compile u-boot
```
git clone https://github.com/OLIMEX/u-boot-olinuxino.git
make ARCH=arm STM32-OLinuXino-LIME_defconfig

make ARCH=arm CROSS_COMPILE=arm-none-linux-gnueabihf- menuconfig
make ARCH=arm CROSS_COMPILE=arm-none-linux-gnueabihf- -j32
```

Output:
`u-boot-spl.stm32` - FSBL binaries with STM32 image header
`u-boot.img` - SSBL

### Prepare an SD card
GPT partitioning (with gdisk or with sgdisk)

  +-------+--------+---------+------------------------+
  | *Num* | *Name* | *Size*  | *Basic boot content*   |
  +=======+========+=========+========================+
  | 1     | fsbl1  | 256 KiB | SPL (u-boot-spl.stm32) |
  +-------+--------+---------+------------------------+
  | 2     | fsbl2  | 256 KiB | SPL (u-boot-spl.stm32) |
  +-------+--------+---------+------------------------+
  | 3     | ssbl   | 2MB     | U-Boot (u-boot.img)    |
  +-------+--------+---------+------------------------+


sgdisk -o /dev/sda

sgdisk --resize-table=128 -a 1 \
-n 1:34:545      -c 1:fsbl1 \
-n 2:546:1057    -c 2:fsbl2 \
-n 3:1058:5153   -c 3:ssbl \
-n 4:5154:       -c 4:rootfs -A 4:set:2 \
-p /dev/sda

dd if=u-boot-spl.stm32  of=/dev/sda1
dd if=u-boot-spl.stm32  of=/dev/sda2
dd if=u-boot.img        of=/dev/sda3


### Common commands
part list mmc 0 -bootable bootpart
load mmc 0:${bootpart} $loadaddr /boot/zImage
load mmc 0:${bootpart} $fdt_addr_r /boot/stm32mp1xx-olinuxino-lime.dtb
setenv bootargs root=/dev/mmcblk0p${bootpart} rootwait rw rootfstype=ext4 earlycon console=ttySTM0,115200n8
bootz $loadaddr - $fdt_addr_r



sysboot mmc 0:${bootpart} any ${scriptaddr} /boot/extlinux/extlinux.conf
