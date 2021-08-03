package com.yogurt.vienna.entity;

public class KakaoMessageDTO {

    private String objectType;
    private String text;
    private String webUrl;
    private String mobildWebUrl;
    private String buttonTitle;

    public KakaoMessageDTO(String objectType, String text, String webUrl, String mobildWebUrl, String buttonTitle) {
        this.objectType = objectType;
        this.text = text;
        this.webUrl = webUrl;
        this.mobildWebUrl = mobildWebUrl;
        this.buttonTitle = buttonTitle;
    }

    public KakaoMessageDTO() {
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getMobildWebUrl() {
        return mobildWebUrl;
    }

    public void setMobildWebUrl(String mobildWebUrl) {
        this.mobildWebUrl = mobildWebUrl;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }
}
