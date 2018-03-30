package com.caiw.dao.impl;


import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 14:42 2018/3/30
 */
public class StockTermDaoImpl {

    public List<String> getScreenTerm(String stockCode){
        List<String> termsList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select " +
                    " b.comment_id,b.sentence_id,count(1) as `num`,b.sentence_nature,GROUP_CONCAT(b.sentence) as terms " +
                    " from " +
                    " (select " +
                    " GROUP_CONCAT(a.nature) as `sentence_nature` ,GROUP_CONCAT(a.stock_term) as `sentence`,a.comment_id,a.sentence_id " +
                    " from " +
                    " (SELECT " +
                    " comment_id,sentence_id,nature,stock_term,stock_code " +
                    " from stock_term s  " +
                    " where s.stock_code = ? " +
                    " ORDER BY s.comment_id,s.sentence_id,s.stock_term_id ) as `a`" +
                    " GROUP BY a.comment_id,a.sentence_id) as `b` " +
                    " GROUP BY b.sentence_nature HAVING `num`>70 ORDER BY num desc;";
            ps = con.prepareStatement(sql);
            JdbcUtil.setValues(ps,stockCode);
            rs = ps.executeQuery();
            while(rs.next()){
                String terms = rs.getString("terms");
                termsList.add(terms);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,rs);
        }
        return termsList;
    }
}
