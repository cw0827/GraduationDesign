package com.caiw.utils;

import com.caiw.entity.Comment;
import com.caiw.entity.StockTerm;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 蔡维 in 13:12 2018/3/24
 * 分词工具类
 */
public class TermUtil {
    public final static Logger log = LoggerFactory.getLogger(TermUtil.class);
    /**
     * 对文本进行分词
     * @param comment 评论
     * @return
     */
    public static List<StockTerm> cutTerm(Comment comment){
        List<StockTerm> stockTermList = new ArrayList<>();
        //句子id,最开始为1。
        int sentenceId = 1;
        //对文章进行分句
        String[] splitOne = comment.getComment().split(";");
        for (String s :splitOne) {
            String[] stringTwo = s.split(",");
            for (String str:stringTwo) {
                //对词进行分词
//                List<Word> words = WordSegmenter.seg(str);
//                PartOfSpeechTagging.process(words);
                Result parse = ToAnalysis.parse(str);
                List<Term> words = parse.getTerms();
                for (int i = 0 ;i < words.size(); i++) {
                        StockTerm stockTerm = new StockTerm();
                        stockTerm.setId(UUID.randomUUID().toString());
                        stockTerm.setStockCode(comment.getStockCode());
                        stockTerm.setCommentId(comment.getId());
                        stockTerm.setSentenceId(sentenceId);
//                        stockTerm.setStockTerm(words.get(i).getText());
                        stockTerm.setStockTerm(words.get(i).getName());
//                        log.info(words.get(i).getText());
                        log.info(words.get(i).getName());
                        stockTerm.setStockTermId(i+1);
//                        stockTerm.setNature(words.get(i).getPartOfSpeech().getDes());
                        stockTerm.setNature(words.get(i).getNatureStr());
                        stockTerm.setCreateTime(new Date(System.currentTimeMillis()));
                        stockTermList.add(stockTerm);
                }
                sentenceId++;
            }
        }
        return stockTermList;
    }
}
