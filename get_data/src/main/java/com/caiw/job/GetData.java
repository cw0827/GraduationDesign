package com.caiw.job;

import com.caiw.dao.impl.CommentDaoImpl;
import com.caiw.dao.impl.StockTermDaoImpl;
import com.caiw.entity.Comment;
import com.caiw.entity.Comment;
import com.caiw.entity.StockTerm;
import com.caiw.utils.ArtJsoup;
import com.caiw.utils.FileUtil;
import com.caiw.utils.TermUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 蔡维 in 17:10 2018/3/6
 */
public class GetData {
    public final static Logger log = LoggerFactory.getLogger(GetData.class);

    public static void main(String[] args) throws IOException {
        log.info("请输入股票代码：");
        Scanner scanner = new Scanner(System.in);
        String stockCode = scanner.nextLine();
        //调用方法爬取数据
        List<Comment> commentList = ArtJsoup.getComment(stockCode);
        //将数据进行存储(原始数据存储)（1、存进txt文件。2、存进kafka---待定---实时分词。3、存进MySql--手动分词）
        //1、存进txt文件   一篇文章放在一个txt文件中
        FileUtil.saveComment(commentList);
        //2、存进mysql comment表(字段：id,stockCode,comment,create_time )
        CommentDaoImpl commentDao = new CommentDaoImpl();
        Boolean saveFlag = commentDao.saveComment(commentList);
        if(saveFlag){
            log.info("存入数据库成功！");
        }else {
            log.info("存入数据库失败！");
        }
        //1、分词得到每个词的信息：文章编号  句编号  词编号  词   词性    //分词使用的word分词，训练模型  加载资源较慢
        StockTermDaoImpl stockTermDao = new StockTermDaoImpl();
        boolean flag = false;
        for (Comment comment:commentList) {
            List<StockTerm> stockTermList = TermUtil.cutTerm(comment);
            //3、存入数据库stockTerm表
            flag = stockTermDao.saveStockTerm(stockTermList);
        }
        if(flag){
            log.info("分词以及存入数据库成功");
        }else {
            log.info("分词以及存入数据库成功");
        }

    }
}