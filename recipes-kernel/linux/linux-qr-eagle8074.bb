# This file was derived from oe-core/meta-qr-linux/meta-som8064/recipes-kernel/linux/linux-qr-som8064.bb

require include/linux-caf.inc

FILESPATH =+ "${WORKSPACE}:"
SRC_URI = "git://codeaurora.org/kernel/msm;tag=LNX.LA.3.5.2-09410-8x74.0;protocol=git;bareclone=1;nobranch=1"
SRC_URI += "file://defconfig \
            file://${MACHINE}.scc \
            file://${MACHINE}-user-config.cfg \
            file://bluetooth.patch;apply=no \
           "

SRC_URI += "https://releases.linaro.org/14.09/ubuntu/ifc6410/initrd.img-3.4.0-linaro-ifc6410;downloadfilename=initrd.img;name=initrd"
SRC_URI[initrd.md5sum] = "d92fb01531698e30615f26efa2999c6c"
SRC_URI[initrd.sha256sum] = "d177ba515258df5fda6d34043261d694026c9e27f1ef8ec16674fa479c5b47fb"

LINUX_VERSION ?= "3.4"
LINUX_VERSION_EXTENSION ?= "-${MACHINE}"

PR = "r0"
PV = "${LINUX_VERSION}"

GCCVERSION="4.8%"

COMPATIBLE_MACHINE_eagle8074 = "eagle8074"
LINUX_VERSION_EXTENSION_eagle8074 = "-eagle8074"

PROVIDES += "kernel-module-cfg80211"

python do_rem_old_linux () {
    import os
    os.system("rm -rf %s/linux-v4.2.6" % d.getVar('DL_DIR', True))
}

do_after_unpack() {
    rm -f ${WORKDIR}/bluetooth.patch.done
}

addtask rem_old_linux after do_cleansstate before do_cleanall
