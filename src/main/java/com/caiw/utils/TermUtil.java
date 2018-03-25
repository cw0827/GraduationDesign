package com.caiw.utils;

import com.caiw.entity.Article;
import com.caiw.entity.StockTerm;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 13:12 2018/3/24
 * 分词工具类
 */
public class TermUtil {
    /**
     * 对文本进行分词
     * @param article 文章
     * @return
     */
    public static List<StockTerm> cutTerm(Article article){
        List<StockTerm> stockTermList = new ArrayList<>();
        //句子id,最开始为1。
        int sentenceId = 1;
        //对文章进行分句
        String[] splitOne = article.getArt().split("。");
        for (String s :splitOne) {
            String[] stringTwo = s.split("，");
            for (String str:stringTwo) {
                //对词进行分词
                List<Word> words = WordSegmenter.seg(str);
                PartOfSpeechTagging.process(words);
                for (int i = 0 ;i < words.size(); i++) {
                    //存单个词
                    StockTerm stockTerm = new StockTerm();
                    stockTerm.setArticleId(article.getId());
                    stockTerm.setSentenceId(sentenceId);
                    stockTerm.setStockTermId(i+1);
                    stockTerm.setStockTerm(words.get(i).getText());
                    stockTerm.setNature(words.get(i).getPartOfSpeech().getDes());
                    stockTermList.add(stockTerm);
                }
                sentenceId++;
            }
        }
        return stockTermList;
    }
}
