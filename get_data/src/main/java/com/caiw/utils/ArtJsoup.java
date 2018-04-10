package com.caiw.utils;

import com.caiw.entity.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Created by 蔡维 in 18:20 2018/3/6
 */
public class ArtJsoup {
    /**
     * 获取评论内容，时间,代码
     * @param
     */
    public static List<Comment> getComment(String stockCode) {
        Logger log = LoggerFactory.getLogger(ArtJsoup.class);
        Document document = null;
        List<Comment> commentList = new ArrayList<>();
        for (int i = 1 ;i< 100000;i++){
            try {
                document = Jsoup.connect("http://stock.quote.stockstar.com/stockinfo_info/comment.aspx?code="+stockCode+"&pageid="+i).get();
                Elements tbody = document.select("tbody");
                Elements align_left = tbody.get(1).getElementsByClass("align_left");
                for (Element element: align_left) {
                    String commentStr = element.text();
                    if( commentStr.equals("--") || commentStr.equals("") ) {
                        continue;
                    }
                    Comment comment = new Comment();
                    //设置评论id
                    comment.setId(UUID.randomUUID().toString());
                    //获取评论代码
                    comment.setStockCode(stockCode);
                    //获取评论内容
                    comment.setComment(commentStr);
                    log.info(commentStr);
                    //爬取时间
                    comment.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
                    //存进kafka（现在是公司的kafka）
                    KafkaUtil.kafkaProduce(comment);
                    commentList.add(comment);
                }
                Elements n = document.getElementsByClass("n");
                if(n.size() == 0){
                    break;
                }
            } catch (Exception e) {
                System.out.println("爬取失败");
            }
        }
        return commentList;
    }

//    public static void main(String[] args) {
//        List<Comment> comment = getComment("900905");
//        System.out.println(comment);
//    }
}
