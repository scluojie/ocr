package com.hxcy.ocr.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author kevin
 * @date 2022/5/23
 * @desc
 */
public class PropertiesUtil {
    private Properties props;

    public PropertiesUtil() {
    }

    /**
     * 加载配置文件
     *
     * @param fileName
     */
    public void readResourceProperties(String fileName) {
        try {
            props = new Properties();
            // 读取 对应properties 中的数据,注意这里的地址是直接在resources下，
            // 如果再resources下的目录中，那么下面的 路径前也要加上该目录
            InputStreamReader inputStream = new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
            props.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readProperties(String file) {
        try {
            props = new Properties();
            props.load(new InputStreamReader(new FileInputStream(new File(file)),"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取对应的value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return props.getProperty(key);
    }

    /**
     * 得到所有的配置信息
     *
     * @return
     */
    public Map<String, String> getAll() {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }
}
