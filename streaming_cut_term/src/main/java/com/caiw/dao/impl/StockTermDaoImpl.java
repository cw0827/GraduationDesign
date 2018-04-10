package com.caiw.dao.impl;

import com.caiw.entity.StockTerm;
import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by 蔡维 in 13:01 2018/3/24
 */
public class StockTermDaoImpl {
    /**
     * 将一些词存入数据库
     * @param stockTermList 词列表
     * @return
     */
    public boolean saveStockTerm(List<StockTerm> stockTermList){
        boolean flag = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JdbcUtil.getCon();
            for (StockTerm stockTerm : stockTermList) {
                String sql = "INSERT INTO stock_term VALUE (?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                JdbcUtil.setValues(ps,stockTerm.getId(),stockTerm.getStockCode(),stockTerm.getCommentId(),stockTerm.getSentenceId()
                ,stockTerm.getStockTermId(),stockTerm.getStockTerm(),stockTerm.getNature(),stockTerm.getCreateTime());
                int num = ps.executeUpdate();
                if(num == 0){
                    flag = false;
                    break;
                }else {
                    flag = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,null);
        }
        return flag;
    }
}
