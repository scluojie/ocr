package com.hxcy.ocr;

import com.alibaba.excel.EasyExcel;
import com.hxcy.ocr.entity.Vmware;
import com.hxcy.ocr.utils.FileUtil;
import com.hxcy.ocr.utils.OCRUtil;
import com.hxcy.ocr.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc  java -Dconfig=D:/Users/13090/Desktop/application.properties -jar ocr-1.0-SNAPSHOT-jar-with-dependencies.jar
 */
public class Job {

    public static  final Logger logger = LoggerFactory.getLogger(Job.class);

    public static String tessPath;
    public static  String excelPath;

    //java -Dconfig=D:\development\ocr\src\main\resources\application.properties -jar web2.jar
    public static void main(String[] args) throws Exception {

        String imagePath;
        PropertiesUtil propertiesUtil;
        Map<String, String> allProps;
        List<Vmware> list = new ArrayList<>();
        //读取配置文件
        //从命令行获取配置文件参数
        String configPath = System.getProperty("config");
        if(null != configPath && !configPath.equals("")){
            if(!new File(configPath).exists()){
                logger.error("配置文件不存在....");
                return;
            }
            propertiesUtil = new PropertiesUtil();
            propertiesUtil.readProperties(configPath);
        }else{
            //读取默认配置文件
            propertiesUtil = new PropertiesUtil();
            propertiesUtil.readResourceProperties("application.properties");
        }

        allProps = propertiesUtil.getAll();
        imagePath =allProps.get("image.path");
        tessPath = allProps.get("tesseract.path");
        excelPath = allProps.get("excel.path");

        if(imagePath ==null || imagePath.equals("")){
            logger.error("picture path is not exists!");
            return;
        }

        List<File> fileList = FileUtil.getFileList(imagePath);

        for (File file : fileList) {
            String text = OCRUtil.getText(file);
            String lineSep = System.getProperty("line.separator");
            String[] money = text.split(lineSep);
            String[] split = file.getAbsolutePath().split("\\\\");
            //money[0].replaceAll("[^0-9.]","")
            String str = money[0].replaceAll("[^0-9.]", " ").trim().replaceAll("\\s+", " ");
            Vmware vmware = new Vmware(split[split.length - 2], Long.parseLong(split[split.length - 3]), Double.parseDouble(str.split(" ")[0]), Double.parseDouble(str.split(" ")[1]), money[0].contains("BNB")? Double.parseDouble(str.split(" ")[2]):0D,  money[0].contains("SOL")? Double.parseDouble(str.split(" ")[2]):0D,LocalDate.now().toString());
            list.add(vmware);
        }

        try {
            String filePath = excelPath + "screenshot_"+LocalDate.now()+ ".xlsx";
            // 删除文件
            File file=new File(filePath);
            if(file.exists()&&file.isFile()){
                file.delete();
            }

            EasyExcel.write(filePath,Vmware.class).sheet("sheet1").doWrite(list);
            logger.info("导出excel success...");
        } catch (Exception e) {
            throw new Exception("生成excel失败");
        }

    }
}
