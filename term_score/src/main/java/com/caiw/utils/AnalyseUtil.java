package com.caiw.utils;

import com.caiw.dao.impl.TermScoreDaoImpl;
import com.caiw.entity.StockScore;
import com.caiw.entity.TermScore;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 蔡维 in 13:01 2018/4/9
 * 对词进行打分
 */
public class AnalyseUtil {
    /**
     * 调用对象
     */
    private static TermScoreDaoImpl termScoreDao = new TermScoreDaoImpl();

    /**
     * 获取评分词列表
     * @param stockCode
     * @return
     */
    public static List<TermScore> getTermScoreList(String stockCode,String[] terms){
        List<TermScore> termScoreList = new ArrayList<>();
        //循环遍历所有的词
        for(String screenTerm : terms){
            TermScore termScore = new TermScore();
            //生成id
            termScore.setId(UUID.randomUUID().toString());
            //股票代码
            termScore.setStockCode(stockCode);
            //词
            termScore.setScreenTerm(screenTerm);
            //通过screen词去数据库找到该词的comment_id
            List<String> commentIds = termScoreDao.getCommentId(screenTerm,stockCode);
            //存入评论中使用该词的次数（就是可能会有很多人用到这个词）
            termScore.setCount(commentIds.size());
            //调用方法通过comment_id获取评论，匹配词语进行打分。
            Double score = getComment(commentIds);
            termScore.setScore(score);
            //设置分分析时间
            termScore.setCreateTime(new Timestamp(System.currentTimeMillis()));
            termScoreList.add(termScore);
        }
        return termScoreList;
    }

    /**
     * 获取评论内容并打分
     * @param commentIds 评论ids
     * @return 分数
     */
    public static Double getComment(List<String> commentIds){
        Double score = 0.0 ;
        for (String commentId : commentIds) {
            String comment = termScoreDao.getComment(commentId);
            //将所有的分加起来
            score += doScore(comment);
        }
        return score;
    }

    /**
     * 打分
     * @param comment 评论内容
     * @return 分数
     */
    public static Double doScore(String comment){
        Double score = 0.0;
        if(isAffirmative(comment)){
            score += 5;
        } else if(isNegative(comment)){
            score += 1;
        }else {
            score += 3;
        }
        return score;
    }

    /**
     * 判断是否包含否定词
     * @param comment 评论内容
     * @return 是/否
     */
    private static boolean isNegative(String comment){
        boolean flag = false;
        String[] negativeWords = GenerateData.getNEGATIVEWORDS();
        for (String negativeWord : negativeWords) {
            if(comment.contains(negativeWord)){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断是否包含肯定词
     * @param comment 评论内容
     * @return 是/否
     */
    private static boolean isAffirmative(String comment){
        boolean flag = false;
        String[] affirmatives = GenerateData.getAFFIRMATIVES();
        for (String affirmative : affirmatives) {
            if(comment.contains(affirmative)){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取股票的分数
     * @param termScoreListByMac 宏观词分数
     * @param termScoreListByMic  微观词分数
     * @param termScoreListByMar    市场词分数
     * @return  股票分数对象
     */
    public static StockScore getStockScore(List<TermScore> termScoreListByMac, List<TermScore> termScoreListByMic, List<TermScore> termScoreListByMar) {
        StockScore stockScore = new StockScore();
        stockScore.setId(UUID.randomUUID().toString());
        stockScore.setStockCode(termScoreListByMac.get(0).getStockCode());
        stockScore.setCreateTime(new Timestamp(System.currentTimeMillis()));
        stockScore.setMacScore(getWeightedMean(termScoreListByMac));
        stockScore.setMicScore(getWeightedMean(termScoreListByMic));
        stockScore.setMarScore(getWeightedMean(termScoreListByMar));
        return stockScore;
    }


    /**
     * 获得所有词的加权平均分
     * @param termScoreList 股票词分数列表
     * @return 加权平均分
     */
    private static Double getWeightedMean(List<TermScore> termScoreList) {
        Double score = 0.0;
        int count = 0;
        for (TermScore termScore : termScoreList) {
            score += termScore.getScore();
            count += termScore.getCount();
        }
        if(count != 0){
            return score/count;
        }else {
            return 0.0;
        }
    }



}
