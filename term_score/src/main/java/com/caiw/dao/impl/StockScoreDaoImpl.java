package com.caiw.dao.impl;

import com.caiw.entity.StockScore;
import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by 蔡维 in 14:31 2018/4/15
 */
public class StockScoreDaoImpl {

    /**
     * 将股票分数存入数据库
     * @param stockScore 股票分数对象
     * @return true存入成功，false存入失败
     */
    public boolean saveScoreDao(StockScore stockScore) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "INSERT INTO stock_score VALUE (?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            JdbcUtil.setValues(ps,stockScore.getId(),stockScore.getStockCode(),stockScore.getMacScore(),stockScore.getMicScore()
            ,stockScore.getMarScore(),stockScore.getCreateTime());
            int i = ps.executeUpdate();
            if(i != 0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,null);
        }
        return  flag;
    }
}
