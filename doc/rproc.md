# Start Remote Proc from U-Boot
part list mmc 0 -bootable bootpart
load mmc 0:${bootpart} ${loadaddr} /lib/firmware/rproc-m4-fw
rproc init
rproc load 0 ${loadaddr} ${filesize}
rproc start 0

# Start Remote Proc from Linux
echo start > /sys/class/remoteproc/remoteproc0/state
cat /sys/class/remoteproc/remoteproc0/state
