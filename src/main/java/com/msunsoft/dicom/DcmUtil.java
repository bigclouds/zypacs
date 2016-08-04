package com.msunsoft.dicom;

import java.awt.image.BufferedImage;

/**
 * DICOM工具类
 */
public class DcmUtil {
    /**
     * 将DICOM文件转换成JPEG
     * @param dicomfile
     * @return BufferedImage
     */
    public static BufferedImage GenJpg(String dicomfile) throws Exception {
        return Dcm2Jpg.GenJpg(dicomfile);
    }
}
