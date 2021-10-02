package com.yogurt.vienna.dto;

public class UserDTO {

    /** DAO에서 받아온 데이터를 담는 포맷 객체로서의 DTO */

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
