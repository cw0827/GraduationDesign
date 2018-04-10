package com.caiw.entity;

import java.sql.Date;

/**
 * Created by 蔡维 in 12:43 2018/3/7
 */
public class Comment {
    private String id;
    private String stockCode;
    private String comment;
    private Date createTime;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return id+"\t"+stockCode+"\t"+comment+"\t"+createTime;
    }
}
