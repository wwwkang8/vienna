package com.yogurt.vienna.dto.Kakao;

import java.util.Map;

public class ContentDTO {

    private String title;
    private String description;
    private String image_url;
    private String image_width;
    private String image_height;
    private Map<String, String> link;

    public ContentDTO() {
    }

    public ContentDTO(String title, String description, String image_url, String image_width, String image_height, Map<String, String> link) {
        this.title = title;
        this.description = description;
        this.image_url = image_url;
        this.image_width = image_width;
        this.image_height = image_height;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_width() {
        return image_width;
    }

    public void setImage_width(String image_width) {
        this.image_width = image_width;
    }

    public String getImage_height() {
        return image_height;
    }

    public void setImage_height(String image_height) {
        this.image_height = image_height;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}
