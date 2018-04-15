package com.caiw.dao.impl;

import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 12:49 2018/4/9
 */
public class TermScoreDaoImpl {

    /**
     * 通过词获取comment_id
     * @param screenTerm 筛选的词
     * @param stockCode 股票代码
     * @return
     */
    public List<String> getCommentId(String screenTerm, String stockCode) {
        List<String> commentIds = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select comment_id from stock_term where stock_code = ? and nature = 'n' and stock_term = ? GROUP BY comment_id;";
            ps = con.prepareStatement(sql);
            JdbcUtil.setValues(ps,stockCode,screenTerm);
            rs = ps.executeQuery();
            while(rs.next()){
                String commentId = rs.getString("comment_id");
                commentIds.add(commentId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,rs);
        }
        return commentIds;
    }

    /**
     * 通过commentId获取comment内容
     * @param commentId 评论id
     * @return
     */
    public String getComment(String commentId) {
        String comment = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select `comment` from `comment` where id = ?;";
            ps = con.prepareStatement(sql);
            JdbcUtil.setValues(ps,commentId);
            rs = ps.executeQuery();
            while(rs.next()){
                comment = rs.getString("comment");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,rs);
        }
        return comment;
    }
}
