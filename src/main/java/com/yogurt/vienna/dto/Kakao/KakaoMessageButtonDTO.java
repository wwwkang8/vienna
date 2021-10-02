package com.yogurt.vienna.dto.Kakao;

import java.util.Map;

public class KakaoMessageButtonDTO {

    private String title;
    private Map<String, String> link;

    public KakaoMessageButtonDTO() {
    }

    public KakaoMessageButtonDTO(String title, Map<String, String> link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }
}
