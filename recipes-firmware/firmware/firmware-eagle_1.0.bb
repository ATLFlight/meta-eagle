SUMMARY = "Eagle board firmware"
DESCRIPTION = "Eagle board firmware (Snapdragon Flight)"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=c6a37e6531bd1efa2a671aec0dcc7111"

SRC_URI = "file://Flight_BSP_3.0_apq8074-le-1-0_r00015.zip \
          file://LICENSE \
"
SRC_URI[md5sum] = "24e4a2235eefe093f5318b5456746ba7"
SRC_URI[sha256sum] = "9e7b01266b243e5126b183189428dc2baa77f06476f293daefd4c7fdc8c410c4"

inherit allarch

do_install() {
    simg2img ${WORKDIR}/Flight_3.0_apq8074-le-1-0_ap_standard_oem_r00015/Flight_3.0_apq8074-le-1-0_ap_standard_oem_r00015/binaries/Flight_BSP_3.0/cache.img ${WORKDIR}/cache.img.raw
    install -d ${D}/lib/firmware
    for f in `e2ls ${WORKDIR}/cache.img.raw`; do 
	e2cp ${WORKDIR}/cache.img.raw:$f ${D}/lib/firmware
    done
    find ${D}/lib/firmware -type f -exec chmod 644 '{}' ';'
}

FILES_${PN} = "/lib/firmware/"

# QA complains about arch specific files
INSANE_SKIP_${PN} = "arch"
