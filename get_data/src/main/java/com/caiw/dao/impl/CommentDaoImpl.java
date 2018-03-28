package com.caiw.dao.impl;

import com.caiw.entity.Comment;
import com.caiw.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 13:01 2018/3/22
 */
public class CommentDaoImpl {
    /**
     * 将评论存入数据库
     * @param commentList  评论列表
     * @return  返回true则存入数据库，返回false则存入失败
     */
    public Boolean saveComment(List<Comment> commentList) {
        Boolean flag = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JdbcUtil.getCon();
            //id使用UUID
            for (Comment comment:commentList) {
                String sql = "INSERT into comment VALUE(?,?,?,?)";
                ps = connection.prepareStatement(sql);
                JdbcUtil.setValues(ps, comment.getId(),comment.getStockCode(),comment.getComment(),comment.getCreateTime());
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
