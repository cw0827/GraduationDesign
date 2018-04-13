package com.caiw;

import com.caiw.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 蔡维 in 11:55 2018/3/31 0031
 */
public class ArticleJsoup2 {
    private static Logger log = LoggerFactory.getLogger(ArticleJsoup2.class);


    public static List<Article> getTitle(){
        List<Article> articleList = new ArrayList<>();
        try {
                Document document = Jsoup.connect("http://kuaixun.stcn.com/list/kxcj.shtml").get();
                //获取页面需要爬取的部分
                Elements listnews = document.getElementsByClass("mainlist");
                //获取页面需要爬取的部分
                Elements elementsList = listnews.select("li");
                for (Element element : elementsList) {

                    //可根据判断时间是否为当天，不是当天就跳出循环就行

                    //获得标题和地址
                    Elements elementAs = element.select("a");
                    Element elementA = null;
                    if(elementAs.size()>0){
                        elementA = elementAs.get(0);
                        //此处可以修改为爬取文章内容了
                        Article article = getArticleData(elementA.attr("href"));
                        //可以添加一个判断，如果饭回来的值为空的话，可以重新爬去一次，或者为空就不添加到list集合里里面。
                        articleList.add(article);
                        log.info(article.getTitle());
                    }

                }
        }catch (Exception e){
            log.info("爬取标题失败");
            e.printStackTrace();
        }
        return articleList;
    }

    public static Article getArticleData(String url){
        Document document = null;
        Article article = new Article();
        try {
            document = Jsoup.connect(url).get();
            //设置文章id
            article.setId(UUID.randomUUID().toString());
            //获取文章标题
            article.setTitle(document.getElementsByClass("intal_tit").select("h2").text().replace(":","：").replace("?","？"));
            //获取文章来源
            article.setSource(document.getElementsByClass("info").select("a").get(1).text());
            //文章时间
            article.setCreateTime(Date.valueOf(document.getElementsByClass("info").text().substring(0,10)));
            //获取文章内容
            article.setArt(document.getElementsByClass("txt_con").select("p").text());
        } catch (Exception e) {
            log.info("打开文章失败");
        }
        return article;
    }


    /**
     * 证券时报网
     * @param args
     */
    public static void main(String[] args) {
        List<Article> articleList = getTitle();
        for (Article article: articleList) {
            System.out.println(article);
        }
    }


    @Test
    public void test1(){
        for (int i = 0; i < 50; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
