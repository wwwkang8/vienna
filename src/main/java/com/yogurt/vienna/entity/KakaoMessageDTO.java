package com.yogurt.vienna.entity;

import java.util.Map;

public class KakaoMessageDTO {

    private String object_type;
    private String text;
    //private Map<String, String> link;
    private String web_url;
    private String mobile_web_url;
    private String button_title;

    public KakaoMessageDTO() {
    }

    public KakaoMessageDTO(String object_type, String text, String web_url, String mobile_web_url, String button_title) {
        this.object_type = object_type;
        this.text = text;
        this.web_url = web_url;
        this.mobile_web_url = mobile_web_url;
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

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getMobile_web_url() {
        return mobile_web_url;
    }

    public void setMobile_web_url(String mobile_web_url) {
        this.mobile_web_url = mobile_web_url;
    }

    public String getButton_title() {
        return button_title;
    }

    public void setButton_title(String button_title) {
        this.button_title = button_title;
    }
}
