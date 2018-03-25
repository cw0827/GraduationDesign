package com.caiw.entity;

/**
 * Created by 蔡维 in 12:56 2018/3/24
 */
public class StockTerm {
    /**
     * 文章id、
     */
    private String articleId;
    /**
     * 句子id、第几句
     */
    private Integer sentenceId;
    /**
     * 词id，第几个词
     */
    private Integer stockTermId;
    /**
     * 词
     */
    private String stockTerm;
    /**
     * 词性
     */
    private String nature;


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public Integer getStockTermId() {
        return stockTermId;
    }

    public void setStockTermId(Integer stockTermId) {
        this.stockTermId = stockTermId;
    }

    public String getStockTerm() {
        return stockTerm;
    }

    public void setStockTerm(String stockTerm) {
        this.stockTerm = stockTerm;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }


    @Override
    public String toString() {
        return "StockTerm{" +
                "articleId='" + articleId + '\'' +
                ", sentenceId=" + sentenceId +
                ", stockTermId=" + stockTermId +
                ", stockTerm='" + stockTerm + '\'' +
                ", nature='" + nature + '\'' +
                '}';
    }
}
