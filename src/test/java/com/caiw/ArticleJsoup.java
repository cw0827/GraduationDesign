package com.caiw;

import com.caiw.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 蔡维 in 11:25 2018/3/31 0031
 */
public class ArticleJsoup {

    private static Logger log = LoggerFactory.getLogger(ArticleJsoup.class);
    /**
     * 证券之星-->文章内容。通过url获取文章标题、内容、来源
     * @param url
     */
    public static Article getArtData(String url){
        Document document = null;
        Article article = new Article();
        try {
            document = Jsoup.connect(url).get();
            //设置文章id
            article.setId(UUID.randomUUID().toString());
            //获取文章标题
            article.setTitle(document.getElementsByClass("box").select("h1").text().replace(":","：").replace("?","？"));
            //获取文章来源
            article.setSource(document.getElementsByClass("d-author").select("a").get(0).text().replace(" 更多文章>>",""));
            //文章时间
            article.setCreateTime(Date.valueOf(document.getElementById("pubtime_baidu").text().substring(0,10)));
            log.info(document.getElementById("pubtime_baidu").text().substring(0,10));
            //获取文章内容
            Elements elements = document.getElementsByClass("article");
            //判断是否有页数
            if(elements.select("select").isEmpty()){
                //获取文章
                article.setArt(elements.text());
            }else {
                Elements p = elements.select("p");
                StringBuffer buffer = new StringBuffer();
                for (int i = 1; i < p.size();i++){
                    //获取文章(添加分页)
                    buffer.append(p.get(i).text());
                }
                article.setArt(buffer.toString());
            }
        } catch (Exception e) {
            log.info("打开文章失败");
        }
        return article;
    }

    /**
     * 证券之星-->文章标题
     * @return
     */
    public static List<Article> getAllTitle(){
        List<Article> articleList = new ArrayList<>();
        try {
            for (int i=1;i<=10;i++) {
                Document document = Jsoup.connect("http://stock.stockstar.com/list/2_"+i+".shtml").get();
                //获取页面需要爬取的部分
                Elements listnews = document.getElementsByClass("listnews");
                //获取页面需要爬取的部分
                Elements elementsList = listnews.select("li").removeClass("space");
                for (Element element : elementsList) {

                    //可根据判断时间是否为当天，不是当天就跳出循环就行

                    //获得标题和地址
                    Elements elementAs = element.select("a");
                    Element elementA = null;
                    if(elementAs.size()>0){
                        elementA = elementAs.get(0);
                        //此处可以修改为爬取文章内容了
                        Article article = getArtData(elementA.attr("href"));
                        //可以添加一个判断，如果饭回来的值为空的话，可以重新爬去一次，或者为空就不添加到list集合里里面。
                        articleList.add(article);
                        log.info(article.getTitle());
                    }

                }
            }
        }catch (Exception e){
            log.info("爬取标题失败");
            e.printStackTrace();
        }
        return articleList;
    }


    public static void main(String[] args) {
        /**
         * 证券之星-->证券要闻
         * http://stock.stockstar.com/list/2_1.shtml
         */
        List<Article> allTitle = getAllTitle();
        for (Article article: allTitle) {
            System.out.println(article);
        }


    }
}
