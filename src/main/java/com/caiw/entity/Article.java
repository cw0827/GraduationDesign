package com.caiw.entity;

/**
 * Created by 蔡维 in 12:43 2018/3/7
 */
public class Article {
    private String title;
    private String source;
    private String Art;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArt() {
        return Art;
    }

    public void setArt(String art) {
        Art = art;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", Art='" + Art + '\'' +
                '}';
    }
}
