package com.caiw.dao.impl;

import com.caiw.entity.Article;
import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 13:01 2018/3/22
 */
public class ArticleDaoImpl {

    /**
     * 将多篇文章存入数据库
     * @param articleList  文章列表
     * @return  返回true则存入数据库，返回false则存入失败
     */
    public Boolean saveArt(List<Article> articleList){
        Boolean flag = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JdbcUtil.getCon();
            //id使用UUID
            for (Article article:articleList) {
                String sql = "INSERT into article VALUE(?,?,?)";
                ps = connection.prepareStatement(sql);
                JdbcUtil.setValues(ps, article.getId(),article.getTitle(),article.getArt());
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
            JdbcUtil.close(connection, ps, null);
        }
        return flag;
    }

    /**
     * 获取文章列表
      * @return 文章列表
     */
    public List<Article> getArt(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> articleList = new ArrayList<>();
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT * from article";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Article article = new Article();
                article.setId(rs.getString("id"));
                article.setTitle(rs.getString("title"));
                article.setArt(rs.getString("content"));
                articleList.add(article);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(con,ps,rs);
        }
        return articleList;
    }
}
