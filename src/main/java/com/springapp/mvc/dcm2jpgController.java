package com.springapp.mvc;

import com.msunsoft.dicom.DcmUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 将DICOM文件生成JPEG的控制类
 */
@Controller

public class dcm2jpgController {

    @RequestMapping("/GenJpg")
    public void GenJpg(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {


            BufferedImage jpeg = DcmUtil.GenJpg(request.getParameter("dcmfile"));
            ImageIO.write(jpeg,"JPEG",jpegOutputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(jpegOutputStream.toByteArray());
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
