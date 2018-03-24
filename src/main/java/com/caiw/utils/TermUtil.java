package com.caiw.utils;

import com.caiw.entity.Article;
import com.caiw.entity.StockTerm;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

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
    public List<StockTerm> cutTerm(Article article){


        Result parse = ToAnalysis.parse(article.getArt());
        List<Term> terms = parse.getTerms();
        for (Term term:terms) {
            System.out.println(term.getName()+'\t'+term.getNatureStr());
        }


        return null;
    }
}
