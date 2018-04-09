package com.caiw.utils;

/**
 * 生成数据的工具类
 */
public class GenerateData {

    //读取properties文件的对象
    private static PropertiesUtil pu = new PropertiesUtil("terms.properties");
    //肯定词
    private static final String AFFIRMATIVES[];
    //否定词
    private static final String NEGATIVEWORDS[];
    //筛选出来的词
    private static final String SCREENWORDS[];

    static {
        //肯定词
        AFFIRMATIVES = pu.getPropertiesData("affirmative").split(" ");
        //否定词
        NEGATIVEWORDS = pu.getPropertiesData("negativeWords").split(" ");
        //筛选出来的词
        SCREENWORDS = pu.getPropertiesData("screenWords").split(" ");
    }


    public static PropertiesUtil getPu() {
        return pu;
    }

    public static String[] getAFFIRMATIVES() {
        return AFFIRMATIVES;
    }

    public static String[] getNEGATIVEWORDS() {
        return NEGATIVEWORDS;
    }

    public static String[] getSCREENWORDS() {
        return SCREENWORDS;
    }
}
