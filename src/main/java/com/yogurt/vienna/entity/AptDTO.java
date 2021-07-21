package com.yogurt.vienna.entity;

public class AptDTO {

    private String aptAdrs ; //Apt adress
    private String aptName ; //Apt Name
    private int aptPrice;    //Apt Price

    public AptDTO(String aptAdrs, String aptName, int aptPrice) {
        this.aptAdrs = aptAdrs;
        this.aptName = aptName;
        this.aptPrice = aptPrice;
    }

    public String getAptAdrs() {
        return aptAdrs;
    }

    public void setAptAdrs(String aptAdrs) {
        this.aptAdrs = aptAdrs;
    }

    public String getAptName() {
        return aptName;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public int getAptPrice() {
        return aptPrice;
    }

    public void setAptPrice(int aptPrice) {
        this.aptPrice = aptPrice;
    }
}
