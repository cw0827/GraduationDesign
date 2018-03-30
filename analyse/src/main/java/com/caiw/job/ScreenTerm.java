package com.caiw.job;

import com.caiw.dao.impl.StockTermDaoImpl;
import com.caiw.utils.AnalyseUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 蔡维 in 14:54 2018/3/30
 */
public class ScreenTerm {
    public static void main(String[] args) {
        StockTermDaoImpl stockTermDao = new StockTermDaoImpl();
        List<String> termsList = stockTermDao.getScreenTerm("900905");
        //拿到筛选后的词
        List<String> termList = new ArrayList<>();
        for (String terms: termsList) {
            String[] split = terms.split(",");
            termList.addAll(Arrays.asList(split));
        }
        //对词进行第二轮筛选 screenTermList为筛选结果

        List<String> screenTermList = AnalyseUtil.doScreen(termList);
        System.out.println(screenTermList);
    }
}
