package com.caiw.job;

import com.caiw.dao.impl.ArticleDaoImpl;
import com.caiw.entity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡维 in 12:44 2018/3/24
 */
public class CutTerm {
    public static void main(String[] args) {
        ArticleDaoImpl articleDao = new ArticleDaoImpl();
        //1、从数据库拿数据   需要文章编号，文章内容
        List<Article> articleList = articleDao.getArt();
        //2、分词得到每个词的信息：文章编号  句编号  词编号  词   词性


        //3、存入数据库Term表


    }
}