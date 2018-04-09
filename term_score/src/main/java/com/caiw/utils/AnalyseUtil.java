package com.caiw.utils;

import com.caiw.dao.impl.TermScoreDaoImpl;
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
    public static List<TermScore> getTermScoreList(String stockCode){
        //获取出筛选的词
        String[] screenWords = GenerateData.getSCREENWORDS();
        List<TermScore> termScoreList = new ArrayList<>();
        //循环遍历所有的词
        for(String screenTerm : screenWords){
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
}
