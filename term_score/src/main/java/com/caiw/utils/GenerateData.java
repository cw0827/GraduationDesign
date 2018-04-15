package com.caiw.utils;

/**
 * 生成数据的工具类
 */
public class GenerateData {

    //读取properties文件的对象
    private static PropertiesUtil pu = new PropertiesUtil("terms.properties");
    //股票代码
    private static final String STOCKCODE;
    //肯定词
    private static final String AFFIRMATIVES[];
    //否定词
    private static final String NEGATIVEWORDS[];
    //宏观层面词
    private static final String MACROSCOPICTERMS[];
    //微观层面词
    private static final String MICROCOSMICTERMS[];
    //市场层面词
    private static final String MARKETTERMS[];

    static {
        //股票代码
        STOCKCODE = pu.getPropertiesData("stockCode");
        //肯定词
        AFFIRMATIVES = pu.getPropertiesData("affirmative").split(" ");
        //否定词
        NEGATIVEWORDS = pu.getPropertiesData("negativeWords").split(" ");
        //宏观层面词
        MACROSCOPICTERMS = pu.getPropertiesData("macroscopicTerms").split(" ");
        //微观层面词
        MICROCOSMICTERMS = pu.getPropertiesData("microcosmicTerms").split(" ");
        //市场层面词
        MARKETTERMS = pu.getPropertiesData("marketTerms").split(" ");
    }

    public static String[] getAFFIRMATIVES() {
        return AFFIRMATIVES;
    }

    public static String[] getNEGATIVEWORDS() {
        return NEGATIVEWORDS;
    }

    public static String getSTOCKCODE() {
        return STOCKCODE;
    }

    public static String[] getMACROSCOPICTERMS() {
        return MACROSCOPICTERMS;
    }

    public static String[] getMICROCOSMICTERMS() {
        return MICROCOSMICTERMS;
    }

    public static String[] getMARKETTERMS() {
        return MARKETTERMS;
    }
}
