package com.hxcy.ocr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc
 */
public class FileUtil {

    public static  final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static List<File> fileList = new ArrayList<>();

    public static List<File> getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("png")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    logger.info("image path:---" + strFileName);
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }
        }
        return fileList;
    }
}
