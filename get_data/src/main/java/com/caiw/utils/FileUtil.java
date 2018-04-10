package com.caiw.utils;



import com.caiw.entity.Comment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by 蔡维 in 14:43 2018/3/7
 */
public class FileUtil {
    /**
     * 存进txt文件   放在一个txt文件中
     * @param commentList 评论集合
     */
    public static void saveComment(List<Comment> commentList) {
        if(commentList.size() != 0){
            //打开文件
            String stockCode = commentList.get(0).getStockCode();
            File file = new File("E:\\成都大学\\毕业设计\\data\\"+stockCode+"股票评论数据.txt");
            //如果文件不存在，先创建文件
            if(!(file.exists())){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println("创建"+stockCode+".txt文件失败");
                    e.printStackTrace();
                }
            }
            //写数据进文件
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                for (Comment comment:commentList) {
                    fw.write(comment.getId()+"\t"+comment.getStockCode()+"\t"+comment.getComment()+"\t"+comment.getCreateTime()+"\n");
                }
                fw.flush();
                fw.close();
            } catch (IOException e) {
                System.err.println(stockCode+"文章写入文件失败");
                e.printStackTrace();
            }
        }
    }

    public static void saveCommentToHdfs(List<Comment> commentList) {
        //股票代码
        String stockCode = commentList.get(0).getStockCode();
        //创建HdfsUtil对象
        HdfsUtil hdfsUtil = new HdfsUtil(stockCode+"股票评论数据.txt");
        StringBuffer buffer = new StringBuffer("");
        for (Comment comment : commentList) {
            buffer.append(comment.getId()).append("\t").append(comment.getStockCode()).append("\t").append(comment.getComment()).append("\t").append(comment.getCreateTime()).append("\n");
        }
        hdfsUtil.writeData(buffer.toString());
    }
}
