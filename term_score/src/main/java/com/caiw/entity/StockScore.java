package com.caiw.entity;

import java.sql.Timestamp;

/**
 * Created by 蔡维 in 14:11 2018/4/15
 */
public class StockScore {
    /**
     * 编号id
     */
    private String id;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 宏观分
     */
    private Double macScore;
    /**
     * 微观分
     */
    private Double micScore;
    /**
     * 市场分
     */
    private Double marScore;
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

    public Double getMacScore() {
        return macScore;
    }

    public void setMacScore(Double macScore) {
        this.macScore = macScore;
    }

    public Double getMicScore() {
        return micScore;
    }

    public void setMicScore(Double micScore) {
        this.micScore = micScore;
    }

    public Double getMarScore() {
        return marScore;
    }

    public void setMarScore(Double marScore) {
        this.marScore = marScore;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "StockScore{" +
                "id='" + id + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", macScore=" + macScore +
                ", micScore=" + micScore +
                ", marScore=" + marScore +
                ", createTime=" + createTime +
                '}';
    }
}
