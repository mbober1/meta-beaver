
## Dependencies
```
sudo dnf install bc bison gcc coccinelle \
  dtc dfu-util flex gdisk graphviz ImageMagick \
  libguestfs-tools ncurses-devel \
  python3-devel openssl-devel openssl-devel-engine lz4 lzma openssl \
  pkg-config python3 python3-asteval python3-coverage python3-filelock \
  python3-pkg-resources python3-pyelftools \
  python3-pytest python3-pytest-xdist \
  python3-subunit python3-testtools \
  python3-virtualenv swig gnutls-devel clang uboot-tools lld llvm
```

## Toolchain
### Download 
```
wget -qO- https:////developer.arm.com/-/media/Files/downloads/gnu/14.2.rel1/binrel/arm-gnu-toolchain-14.2.rel1-x86_64-arm-none-linux-gnueabihf.tar.xz | tar xJ
```

### Source toolchain
```
TOOLCHAIN=./arm-gnu-toolchain-14.2.rel1-x86_64-arm-none-linux-gnueabihf
PATH="$(readlink -f $TOOLCHAIN/bin)":$PATH
```
