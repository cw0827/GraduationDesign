package com.caiw.utils;

import com.caiw.entity.Article;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by 蔡维 in 14:43 2018/3/7
 */
public class FileUtil {
    /**
     * 存进txt文件   一篇文章放在一个txt文件中
     * @param articleList 文章集合
     */
    public static void saveArt(List<Article> articleList){
        for (Article article:articleList) {
            //打开文件
            File file = new File("E:\\成都大学\\毕业设计\\data\\"+article.getTitle()+".txt");
            //如果文件不存在，先创建文件
            if(!(file.exists())){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println("创建"+article.getTitle()+".txt文件失败");
                    e.printStackTrace();
                }
            }
            //写数据进文件  格式
            //标题
            //来源
            //内容
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                fw.write(article.getTitle()+"\n"+article.getSource()+"\n"+article.getArt());
                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.err.println(article.getTitle()+"文章写入文件失败");
                e.printStackTrace();
            }
        }
    }
}
