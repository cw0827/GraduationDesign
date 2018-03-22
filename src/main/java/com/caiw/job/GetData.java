package com.caiw.job;

import com.caiw.dao.impl.ArticleDaoImpl;
import com.caiw.entity.Article;
import com.caiw.utils.ArtJsoup;
import com.caiw.utils.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by 蔡维 in 17:10 2018/3/6
 */
public class GetData {
    public static void main(String[] args) throws IOException {
        //调用方法爬取数据
        List<Article> articleList = ArtJsoup.getAllTitle();
        //将数据进行存储(原始数据存储)（1、存进txt文件。2、存进kafka---待定）
        //1、存进txt文件   一篇文章放在一个txt文件中
        FileUtil.saveArt(articleList);
        //2、存进mysql article表(字段：id,title,content )
        Boolean saveFlag = ArticleDaoImpl.saveArt(articleList);
        if(saveFlag){
            System.out.println("存入数据库成功！");
        }else {
            System.out.println("存入数据库失败！");
        }
    }
}
