package com.caiw.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by 蔡维 in 15:15 2018/3/30
 */
public class JsoupUtil {
    public final static Logger log = LoggerFactory.getLogger(JsoupUtil.class);
    /**
     * 爬取关键字再baidu.com中返回搜索条数
     * @param keyword 关键字
     */
    public static Double getPageNum(String keyword){
        Double pageNum = null;
        Document document;
        try {
            document = Jsoup.connect("http://www.baidu.com/s?wd=" + keyword).get();
            Elements elements = document.getElementsByClass("nums");
            String regex = "\\d+(\\d+)+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(elements.text().replaceAll(",",""));
            while(matcher.find()){
                String selectNum = matcher.group();
                log.info("百度搜索'"+keyword+"'出来的结果个数为："+selectNum);
                pageNum = Math.rint(Double.parseDouble(selectNum)/10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageNum;
    }




    public static void main(String[] args) {
        Double selectNum = getPageNum("压力线 蛋糕");
        System.out.println(selectNum);
    }
}
