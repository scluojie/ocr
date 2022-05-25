package com.hxcy.ocr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc
 */
public class FileUtil {

    public static  final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static List<File> fileList = new ArrayList<>();
    public static Map<String,File> spendingFileMap = new HashMap<>();
    public static Map<String,File> energyFileMap = new HashMap<>();

    public static void getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("png")) { // 判断文件名是否以.png结尾
                    String strFileName = files[i].getAbsolutePath();
                    logger.info("image path:---" + strFileName);
                    String[] split = files[i].getAbsolutePath().split("\\\\");
                    String text = OCRUtil.getText(files[i]);
                    //判断是Speeding 还是Energy
                    if(text.contains("GST") & text.contains("GMT")) {
                        //Speeding
                        //当前fileName比之前的大就覆盖
                        if (compare(getFileName(files[i]), getFileName(spendingFileMap.get(split[split.length - 2]) != null ? spendingFileMap.get(split[split.length - 2]) : new File("0.png")))) {
                            spendingFileMap.put(split[split.length - 2],files[i]);
                        }
                    }else{
                        String energyText = OCRUtil.getEnergyText(files[i]);
                        if(energyText.contains("GST") & energyText.contains("/")){
                            //Energy
                            if (compare(getFileName(files[i]), getFileName(energyFileMap.get(split[split.length - 2]) != null ? energyFileMap.get(split[split.length - 2]) : new File("0.png")))) {
                                energyFileMap.put(split[split.length - 2],files[i]);
                            }
                        }else{
                            //wallet
                            logger.info("wallet....");
                        }
                    }
                } else {
                    continue;
                }
            }
        }

    }

    public static List<File> getFileList(){
        //合并两个map 的value
        Iterator<File> iterator = spendingFileMap.values().iterator();
        while(iterator.hasNext()) {
            fileList.add(iterator.next());
        }
        Iterator<File> iterator1 = energyFileMap.values().iterator();
        while(iterator1.hasNext()) {
            fileList.add(iterator1.next());
        }
        return fileList;
    }

    public static String  getFileName(File file){
        return file.getName().split("\\.")[0];
    }

    public static boolean compare(String filename1,String filename2){
        if(Long.parseLong(filename1) > Long.parseLong(filename2)){
            return true;
        }else{
            return false;
        }
    }
}
