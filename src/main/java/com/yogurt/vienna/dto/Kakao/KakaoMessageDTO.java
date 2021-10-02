package com.yogurt.vienna.dto.Kakao;

import java.util.List;
import java.util.Map;

public class KakaoMessageDTO {

    private String object_type;
    private String header_title;
    private Map<String, String> header_link;
    private List<ContentDTO> contents;
    private List<KakaoMessageButtonDTO> buttons;

    public KakaoMessageDTO() {
    }

    public KakaoMessageDTO(String object_type, String header_title, Map<String, String> header_link, List<ContentDTO> contents, List<KakaoMessageButtonDTO> buttons) {
        this.object_type = object_type;
        this.header_title = header_title;
        this.header_link = header_link;
        this.contents = contents;
        this.buttons = buttons;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    public String getHeader_title() {
        return header_title;
    }

    public void setHeader_title(String header_title) {
        this.header_title = header_title;
    }

    public Map<String, String> getHeader_link() {
        return header_link;
    }

    public void setHeader_link(Map<String, String> header_link) {
        this.header_link = header_link;
    }

    public List<ContentDTO> getContents() {
        return contents;
    }

    public void setContents(List<ContentDTO> contents) {
        this.contents = contents;
    }

    public List<KakaoMessageButtonDTO> getButtons() {
        return buttons;
    }

    public void setButtons(List<KakaoMessageButtonDTO> buttons) {
        this.buttons = buttons;
    }

    //    public KakaoMessageDTO(String object_type, String text, Map<String, String> link, String button_title) {
//        this.object_type = object_type;
//        this.text = text;
//        this.link = link;
//        this.button_title = button_title;
//    }
//
//    public String getObject_type() {
//        return object_type;
//    }
//
//    public void setObject_type(String object_type) {
//        this.object_type = object_type;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public Map<String, String> getLink() {
//        return link;
//    }
//
//    public void setLink(Map<String, String> link) {
//        this.link = link;
//    }
//
//    public String getButton_title() {
//        return button_title;
//    }
//
//    public void setButton_title(String button_title) {
//        this.button_title = button_title;
//    }
}
