package com.yogurt.vienna.dto;

import java.util.Map;

public class KakaoMessageDTO {

    private String object_type;
    private String text;
    private Map<String, String> link;
    private String button_title;

    public KakaoMessageDTO() {
    }

    public KakaoMessageDTO(String object_type, String text, Map<String, String> link, String button_title) {
        this.object_type = object_type;
        this.text = text;
        this.link = link;
        this.button_title = button_title;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }

    public String getButton_title() {
        return button_title;
    }

    public void setButton_title(String button_title) {
        this.button_title = button_title;
    }
}
