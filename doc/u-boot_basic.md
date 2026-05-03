### Source toolchain
```
TOOLCHAIN=./arm-gnu-toolchain-14.2.rel1-x86_64-arm-none-linux-gnueabihf
PATH="$(readlink -f $TOOLCHAIN/bin)":$PATH
```

## Sources
```
git clone https://source.denx.de/u-boot/u-boot.git --depth 1
cd u-boot
```

## Build
```
export CC=clang
export HOSTCC=clang 
export ARCH=arm
export KBUILD_OUTPUT=output
export CROSS_COMPILE=arm-none-linux-gnueabihf-
make stm32mp15_basic_defconfig
echo 'CONFIG_DEFAULT_DEVICE_TREE="st/stm32mp157a-dk1"' >> output/.config
make -j$(nproc) all
```

## Prepare SD card
- GPT partitioning
- 2 fsbl partitions, named “fsbl1” and “fsbl2”, size at least 256KiB

### Write GPT table
```
Number  Start (sector)    End (sector)  Size       Code  Name
   1              34             545   256.0 KiB   8300  fsbl1
   2             546            1057   256.0 KiB   8300  fsbl2
   3            1058            5153   2.0 MiB     8300  ssbl
   4            5154       124735454   59.5 GiB    8300  rootfs
```

```
sgdisk -o /dev/sda
sgdisk --resize-table=128 -a 1 \
-n 1:34:545       -c 1:fsbl1 \
-n 2:546:1057     -c 2:fsbl2 \
-n 3:1058:5153    -c 3:ssbl \
-n 4:5154:        -c 4:rootfs -A 4:set:2 \
-p /dev/sda
```

### Flash
dd if=output/u-boot-spl.stm32 of=/dev/sda1
dd if=output/u-boot-spl.stm32 of=/dev/sda2
dd if=output/u-boot.img of=/dev/sda3
sync


## Serial console
picocom -b 115200 /dev/serial/by-id/usb-STMicroelectronics_STM32_STLink_066EFF383159503043123056-if01