package com.caiw.job;

import com.caiw.dao.impl.TermScoreDaoImpl;
import com.caiw.entity.TermScore;
import com.caiw.utils.AnalyseUtil;
import com.caiw.utils.GenerateData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 11:47 2018/4/9
 */
public class TermScoreJob {
    /**
     * 对筛选后的词进行打分
     * @param args
     */
    public static void main(String[] args) {
        String stockCode = "900905";
        List<TermScore> termScoreList = AnalyseUtil.getTermScoreList(stockCode);
        for (TermScore termScore : termScoreList) {
            System.out.println(termScore.toString());
        }
    }
}
