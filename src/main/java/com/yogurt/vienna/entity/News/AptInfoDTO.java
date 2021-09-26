package com.yogurt.vienna.entity.News;

public class AptInfoDTO {

    private String aptPrice;
    private String constructYear;
    private String trnYear;
    private String trnMonth;
    private String trnDay;
    private String address1;
    private String address2;
    private String jeonyong;
    private String jibeon;
    private String areaCode;
    private String floor;

    public AptInfoDTO() {
    }

    public AptInfoDTO(String aptPrice, String constructYear, String trnYear, String trnMonth, String trnDay, String address1, String address2, String jeonyong, String jibeon, String areaCode, String floor) {
        this.aptPrice = aptPrice;
        this.constructYear = constructYear;
        this.trnYear = trnYear;
        this.trnMonth = trnMonth;
        this.trnDay = trnDay;
        this.address1 = address1;
        this.address2 = address2;
        this.jeonyong = jeonyong;
        this.jibeon = jibeon;
        this.areaCode = areaCode;
        this.floor = floor;
    }

    public String getAptPrice() {
        return aptPrice;
    }

    public void setAptPrice(String aptPrice) {
        this.aptPrice = aptPrice;
    }

    public String getConstructYear() {
        return constructYear;
    }

    public void setConstructYear(String constructYear) {
        this.constructYear = constructYear;
    }

    public String getTrnYear() {
        return trnYear;
    }

    public void setTrnYear(String trnYear) {
        this.trnYear = trnYear;
    }

    public String getTrnMonth() {
        return trnMonth;
    }

    public void setTrnMonth(String trnMonth) {
        this.trnMonth = trnMonth;
    }

    public String getTrnDay() {
        return trnDay;
    }

    public void setTrnDay(String trnDay) {
        this.trnDay = trnDay;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getJeonyong() {
        return jeonyong;
    }

    public void setJeonyong(String jeonyong) {
        this.jeonyong = jeonyong;
    }

    public String getJibeon() {
        return jibeon;
    }

    public void setJibeon(String jibeon) {
        this.jibeon = jibeon;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
