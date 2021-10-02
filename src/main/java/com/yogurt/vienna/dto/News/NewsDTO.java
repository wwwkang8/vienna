package com.yogurt.vienna.dto.News;

public class NewsDTO {

    private String newsLink;
    private String Text;

    public NewsDTO(String newsLink, String text) {
        this.newsLink = newsLink;
        Text = text;
    }

    public NewsDTO() {
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
