package com.caiw.job;

import com.caiw.dao.impl.ArticleDaoImpl;
import com.caiw.dao.impl.StockTermDaoImpl;
import com.caiw.entity.Article;
import com.caiw.entity.StockTerm;
import com.caiw.utils.TermUtil;

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
        //2、分词得到每个词的信息：文章编号  句编号  词编号  词   词性    //分词使用的word分词，训练模型  加载资源较慢
        StockTermDaoImpl stockTermDao = new StockTermDaoImpl();
        boolean flag = false;
        for (Article article:articleList) {
            List<StockTerm> stockTermList = TermUtil.cutTerm(article);
            //3、存入数据库stockTerm表
            flag = stockTermDao.saveStockTerm(stockTermList);
        }
        if(flag){
            System.out.println("分词以及存入数据库成功！");
        }else {
            System.out.println("分词以及存入数据库失败！");
        }
//        List<StockTerm> stockTermList = TermUtil.cutTerm(articleList.get(0));
//
//        for (StockTerm stockTerm : stockTermList) {
//            System.out.println(stockTerm.toString());
//            System.out.println();
//        }





    }
}
