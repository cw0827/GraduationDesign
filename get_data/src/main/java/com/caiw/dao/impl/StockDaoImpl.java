package com.caiw.dao.impl;

import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 15:49 2018/4/13
 */
public class StockDaoImpl {
    /**
     * 获取所有股票代码
     * @return 股票代码列表
     */
    public List<String> getStockCodes(){
        List<String> stockCodes = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select stock_code from stock where 1=1 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                stockCodes.add(rs.getString("stock_code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,rs);
        }
        return stockCodes;
    }
}
