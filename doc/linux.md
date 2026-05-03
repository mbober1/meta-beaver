### Source toolchain
```
TOOLCHAIN=./arm-gnu-toolchain-14.2.rel1-x86_64-arm-none-linux-gnueabihf
PATH="$(readlink -f $TOOLCHAIN/bin)":$PATH
```

## Clone kernel
```
git clone https://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git --depth 1
cd linux
```

## Build
```
export ARCH=arm
export CROSS_COMPILE=arm-none-linux-gnueabihf-
export LLVM=1

make O=output defconfig
make O=output -j$(nproc) vmlinux dtbs zImage
```

## Create initrd
```
INITRD_PATH=/tmp/initrd
mkdir -p $INITRD_PATH/dev
wget https://github.com/polaco1782/linux-static-binaries/raw/refs/heads/master/armv7l-eabihf/bash -O $INITRD_PATH/init
chmod +x $INITRD_PATH/init
sudo mknod -m 666 $INITRD_PATH/dev/null c 1 3
sudo mknod -m 600 $INITRD_PATH/dev/console c 5 1
sudo chown -R root:root $INITRD_PATH/init $INITRD_PATH/dev
(cd $INITRD_PATH && find | cpio --verbose -o -H newc -R root:root | gzip --best > rootfs.cpio.gz)
mkimage -A arm -O linux -T ramdisk -C gzip -d $INITRD_PATH/rootfs.cpio.gz output/rootfs.cpio.uboot
sudo rm -rf $INITRD_PATH
```

## Flash SD card
```
mkfs.ext4 /dev/sda4
sudo mount -t ext4 /dev/sda4 /mnt
sudo chown $UID:$GID /mnt

mkdir /mnt/boot
cp output/arch/arm/boot/zImage /mnt/boot
cp output/arch/arm/boot/dts/st/stm32mp157a-dk1.dtb /mnt/boot
cp output/rootfs.cpio.uboot /mnt/boot
sync
sudo umount /mnt
```

## Boot linux (inside U-Boot)
```
load mmc 0:4 $loadaddr /boot/zImage
load mmc 0:4 $fdt_addr_r /boot/stm32mp157a-dk1.dtb
load mmc 0:4 $ramdisk_addr_r /boot/rootfs.cpio.uboot
setenv bootargs "root=/dev/ram0 rw rootwait earlycon console=ttySTM0,115200n8 loglevel=8"
bootz $loadaddr $ramdisk_addr_r $fdt_addr_r
```
