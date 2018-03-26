package com.caiw.utils;

import com.caiw.entity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by 蔡维 in 18:20 2018/3/6
 */
public class ArtJsoup {
    /**
     * 通过url获取文章标题、内容、来源
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
//            article.setSource(document.getElementsByClass("d-author").select("a").get(0).text().replace(" 更多文章>>",""));
            //文章时间
            article.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
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
            System.out.println("打开文章失败");
        }
        return article;
    }


//    public static void main(String[] args) {
//        Article artData = getArtData("http://stock.stockstar.com/SS2018030700001090.shtml");
//        System.out.println(artData.toString());
//    }


    public static List<Article> getAllTitle(){
        List<Article> articleList = new ArrayList<>();
        try {
            for (int i=1;i<=10;i++) {
                Document document = Jsoup.connect("http://stock.stockstar.com/list/1033_" + i + ".shtml").get();
                //获取页面需要爬取的部分
                Elements listnews = document.getElementsByClass("listnews");
                //获取页面需要爬取的部分
                Elements ElementsLi = listnews.select("li");
                for (Element element : ElementsLi) {
                    //获取文章时间
                    String[] s = element.select("span").text().split(" ");
                    //匹配时间
                    boolean flag = s[0].equals(DateUtil.dateToTime(new Date()));//DateUtil.dateToTime(new Date())
                    if (flag) {
                        //获得标题和地址
                        Element elementA = element.select("a").get(0);
                        //此处可以修改为爬取文章内容了
//                    System.out.println(elementA.text() + "\t" + elementA.attr("href"));
                        Article article = getArtData(elementA.attr("href"));
                        articleList.add(article);
                        System.out.println(article.getTitle());
                    }
                }
            }
        }catch (Exception e){
            System.err.println("爬取标题失败");
            e.printStackTrace();
        }
        return articleList;
    }
}
