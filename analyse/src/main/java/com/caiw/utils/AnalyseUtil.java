package com.caiw.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by 蔡维 in 16:01 2018/3/30
 */
public class AnalyseUtil {

    /**
     * 典型词  ： 均线 回调 突破	回档 压力线	支撑线
     */
    public static String[] standardTerm = new String[]{"均线","回调","突破","回档","压力线","支撑线"};
    public final static Logger log = LoggerFactory.getLogger(AnalyseUtil.class);
    /**
     * 每一个典型词对应的页面数
     */
    public static Map<String,Double> standardMap = new HashMap<>();

    static {
        for (String str:standardTerm) {
            standardMap.put(str,JsoupUtil.getPageNum(str));
        }
    }
    /**
     * 取对数
     * 页数 = 条数/10
     */
    public static Double getPmiIr(String stockTerm){
        //（两个词一起的页数/分开的页数的乘积），然后取对数
        double hitsOne = JsoupUtil.getPageNum(stockTerm);
        double pmiIrAll = 0;
        for (String str:standardTerm) {
            double hitsTwo= standardMap.get(str);
            double hitOneTwo = JsoupUtil.getPageNum(str+" "+stockTerm);
            pmiIrAll += Math.log(hitOneTwo/(hitsOne*hitsTwo));
        }
        return pmiIrAll/standardTerm.length;
    }

    /**
     * 对词进行筛选
     * @return
     */
    public static Set<String> doScreen(Set<String> termSet){
        Set<String> screenTermSet = new HashSet<>();
        for (String term : termSet) {
            //拿到词，判断他的PMI-IR分数是否再规定值
            Double pmiIr = getPmiIr(term);
            if (pmiIr > -18) {
                screenTermSet.add(term);
            }
        }
        return screenTermSet;
    }




    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("介入2");
        strings.add("介入1");
        strings.add("介入3");
        for (String str: strings) {
            System.out.println(str);
        }
    }
}
