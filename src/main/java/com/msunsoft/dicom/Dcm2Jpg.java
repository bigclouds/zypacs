package com.msunsoft.dicom;

import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Administrator on 2016-08-03.
 */
public class Dcm2Jpg {
    public static BufferedImage GenJpg(String dicomfile) throws IOException, Exception {
        try
        {
            File myDicomFile = new File(dicomfile);
            BufferedImage myJpegImage = null;
            Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
            ImageReader reader = (ImageReader) iter.next();
            DicomImageReadParam param = null;
            try{
                param = (DicomImageReadParam) reader.getDefaultReadParam();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            ImageInputStream iis=ImageIO.createImageInputStream(myDicomFile);
            reader.setInput(iis, false);
            myJpegImage = reader.read(0, param);
            iis.close();
            if (myJpegImage == null) {
                System.out.println("\nError: couldn't read dicom image!");
                return null;
            }
            /*
            File myJpegFile = new File("d:/demo.jpg");
            OutputStream output = new BufferedOutputStream(new FileOutputStream(myJpegFile));
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
            encoder.encode(myJpegImage);
            System.out.println("Image Create successufully");
            output.close();
            */
            return myJpegImage;


        }
        catch(IOException e){
            System.out.println("\nError: couldn't read dicom image!"+ e.getMessage());
            return null;
        }
    }
}
