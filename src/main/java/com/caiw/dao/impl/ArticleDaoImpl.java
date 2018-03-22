package com.caiw.dao.impl;

import com.caiw.entity.Article;
import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

/**
 * Created by 蔡维 in 13:01 2018/3/22
 */
public class ArticleDaoImpl {

    public static Boolean saveArt(List<Article> articleList){
        Boolean flag = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JdbcUtil.getCon();
            //id使用UUID
            for (Article article:articleList) {
                String sql = "INSERT into article VALUE(?,?,?)";
                ps = connection.prepareStatement(sql);
                JdbcUtil.setValues(ps, UUID.randomUUID().toString(),article.getTitle(),article.getArt());
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
}
