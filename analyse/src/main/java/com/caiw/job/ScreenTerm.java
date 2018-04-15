package com.caiw.job;

import com.caiw.dao.impl.StockDaoImpl;
import com.caiw.dao.impl.StockTermDaoImpl;
import com.caiw.entity.StockTerm;
import com.caiw.utils.AnalyseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by 蔡维 in 14:54 2018/3/30
 */
public class ScreenTerm {

    public final static Logger log = LoggerFactory.getLogger(ScreenTerm.class);

    public static void main(String[] args) {
        StockDaoImpl stockDao = new StockDaoImpl();
        List<String> stockCodes = stockDao.getStockCodesAfterJsoup();
        for (String stockCode : stockCodes) {
            log.info("开始筛选股票代码为\t"+stockCode+"\t的词");
            StockTermDaoImpl stockTermDao = new StockTermDaoImpl();
            List<String> termsList = stockTermDao.getScreenTerm(stockCode);
            //拿到筛选后的词 set集合去重（没必要分词一个重复的词，消耗性能、网络io）
            Set<String> termSet = new HashSet<>();
            for (String terms: termsList) {
                String[] split = terms.split(",");
                termSet.addAll(Arrays.asList(split));
            }
            //对词进行第二轮筛选 screenTermList为筛选结果（改为Set集合）
            Set<String> screenTermSet = AnalyseUtil.doScreen(termSet);
            //从数据库找到这些词，然后获取出所有信息,存入screen_term表,调用方法吧
            long insertNum = stockTermDao.getByStockTermToScreen(screenTermSet, stockCode);
            log.info(stockCode+"股票分析后插入筛选表的行数为："+insertNum);
            stockDao.setScreenFlag(stockCode);
        }
        //调用打分的程序，可以用shell去调
    }
}
