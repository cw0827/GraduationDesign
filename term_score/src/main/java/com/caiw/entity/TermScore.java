package com.caiw.entity;

import java.sql.Timestamp;

/**
 * Created by 蔡维 in 11:48 2018/4/9
 */
public class TermScore {
    /**
     * 编号id
     */
    private String id;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 筛选词
     */
    private String screenTerm;
    /**
     * 评论中使用该词次数
     */
    private Integer count;
    /**
     * 该词的分数
     */
    private Double score;
    /**
     * 创建时间
     */
    private Timestamp createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getScreenTerm() {
        return screenTerm;
    }

    public void setScreenTerm(String screenTerm) {
        this.screenTerm = screenTerm;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TermScore{" +
                "id='" + id + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", screenTerm='" + screenTerm + '\'' +
                ", count=" + count +
                ", score=" + score +
                ", createTime=" + createTime +
                '}';
    }
}
