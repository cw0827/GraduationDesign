package com.caiw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties工具类
 * @author 蔡维
 */
public class PropertiesUtil {
    /**
     * 静态properties对象
     */
    private static Properties properties;

    /**
     * 静态代码块，读取文件
     */
    static {
        properties = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/jdbc.properties");
        try{
            properties.load(in);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 获取values
     * @param key
     * @return
     */
    public static String getValues(String key){
            return properties.getProperty(key);
        }


    /**
     * 获取生产者的properties
     * @return
     */
    public static Properties getProdeceProperties(){
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/producer.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 获取消费者的properties
     * @return
     */
    public static Properties getConsumerProperties(){
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream("/consumer.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
