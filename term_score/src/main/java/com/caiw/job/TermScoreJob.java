package com.caiw.job;

import com.caiw.dao.impl.StockDaoImpl;
import com.caiw.dao.impl.StockScoreDaoImpl;
import com.caiw.dao.impl.TermScoreDaoImpl;
import com.caiw.entity.StockScore;
import com.caiw.entity.TermScore;
import com.caiw.utils.AnalyseUtil;
import com.caiw.utils.GenerateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 11:47 2018/4/9
 */
public class TermScoreJob {
    public static final Logger log = LoggerFactory.getLogger(TermScoreJob.class);
    public static StockDaoImpl stockDao = null;
    /**
     * 对筛选后的词进行打分
     * @param args
     */
    public static void main(String[] args) {
        stockDao = new StockDaoImpl();
        List<String> stockCodesAfterScreen = stockDao.getStockCodesAfterScreen();
        for ( String stockCode : stockCodesAfterScreen) {
            getScore(stockCode);
        }
    }
    /**
     * 打分主
     * @param stockCode 股票代码
     */
    public static void getScore(String stockCode){
        log.info("开始给\t" +stockCode+ "\t股票打分");
        //拿到宏观词分数
        List<TermScore> termScoreListByMac = AnalyseUtil.getTermScoreList(stockCode,GenerateData.getMACROSCOPICTERMS());
        //微观词分数
        List<TermScore> termScoreListByMic = AnalyseUtil.getTermScoreList(stockCode,GenerateData.getMICROCOSMICTERMS());
        //市场词分数
        List<TermScore> termScoreListByMar = AnalyseUtil.getTermScoreList(stockCode,GenerateData.getMARKETTERMS());
        //算出加权平均
        StockScore stockScore = AnalyseUtil.getStockScore(termScoreListByMac,termScoreListByMic,termScoreListByMar);
        //存入数据库
        StockScoreDaoImpl stockScoreDao = new StockScoreDaoImpl();
        boolean flag = stockScoreDao.saveScoreDao(stockScore);
        if(flag){
            log.info(stockCode+"\t处理成功！");
            stockDao.setScoreFlag(stockCode);
        }else {
            log.error(stockCode+"\t处理失败！");
        }

    }
}
