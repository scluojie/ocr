package com.hxcy.ocr.utils;

import com.hxcy.ocr.Job;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc
 */
public class OCRUtil {
    public static  final Logger logger = LoggerFactory.getLogger(OCRUtil.class);

    public static String getText(File imageLocation){
        Rectangle rectangle = new Rectangle();
        rectangle.setRect(150,300,500,300);//150 300 500 300
        //rectangle.setRect(500,300,160,300);
        Tesseract instance = new Tesseract();
        instance.setDatapath(Job.tessPath);
        try
        {
            String imgText = instance.doOCR(imageLocation,rectangle);
            return imgText.replaceAll("[oO]", "0").replaceAll("00", "0").replaceAll("S0L","SOL");
        }
        catch (TesseractException e)
        {
            e.getMessage();
            logger.error(e.getMessage());
            return "Error while reading image";
        }
    }
}
