package com.hxcy.ocr.utils;

import com.hxcy.ocr.Job;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc
 */
public class OCRUtil {
    public static final Logger logger = LoggerFactory.getLogger(OCRUtil.class);

    public static String getText(File imageLocation) {
        //Rectangle rectangle = new Rectangle();
        //rectangle.setRect(150, 300, 500, 300);//150 300 500 300
        //rectangle.setRect(500,300,160,300);
        Tesseract instance = new Tesseract();
        instance.setDatapath(Job.tessPath);
        try {
            //String imgText = instance.doOCR(imageLocation, rectangle);
            //return imgText.replaceAll("[oO]", "0").replaceAll("00", "0").replaceAll("S0L", "SOL");
            BufferedImage image = ImageIO.read(imageLocation);

            String imgText = instance.doOCR(convertImage(image, 150, 300, 500, 300));
            logger.info(imgText);
            return imgText;
        } catch (TesseractException e) {
            e.getMessage();
            logger.error(e.getMessage());
            return "Error while reading image";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error while reading image";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while reading image";
        }
    }

    public static String getEnergyText(File imageLocation) {
        //Rectangle rectangle = new Rectangle();
        //rectangle.setRect(200, 800, 180, 50);//150 300 500 300
        //rectangle.setRect(500,300,160,300);
        Tesseract instance = new Tesseract();
        instance.setDatapath(Job.tessPath);
        try {
            //String imgText = instance.doOCR(imageLocation, rectangle);
            BufferedImage image = ImageIO.read(imageLocation);

            String imgText = instance.doOCR(convertImage(image, 200, 800, 180, 100));
            return imgText;
        } catch (TesseractException e) {
            e.getMessage();
            logger.error(e.getMessage());
            return "Error while reading image";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error while reading image";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while reading image";
        }
    }

    //对图片进行处理 - 提高识别度
    public static BufferedImage convertImage(BufferedImage image,Integer xInit,Integer yInit,Integer xOffset,Integer yOffset) throws Exception {
        //按指定宽高创建一个图像副本
        image = ImageHelper.getSubImage(image, xInit, yInit, xOffset, yOffset);
        // 图像转换成灰度的简单方法 - 黑白处理
        image = ImageHelper.convertImageToGrayscale(image);
        // 图像缩放 - 放大n倍图像
        image = ImageHelper.getScaledInstance(image, image.getWidth() * 3, image.getHeight() * 3);
        return image;
    }
}
