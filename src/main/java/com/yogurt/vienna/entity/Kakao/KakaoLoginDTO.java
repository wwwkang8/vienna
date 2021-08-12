package com.yogurt.vienna.entity.Kakao;

public class KakaoLoginDTO {

    private String client_id;
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String refresh_token_expires_in;

    public KakaoLoginDTO() {
    }

    public KakaoLoginDTO(String client_id, String access_token, String token_type, String refresh_token, String expires_in, String refresh_token_expires_in) {
        this.client_id = client_id;
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.refresh_token_expires_in = refresh_token_expires_in;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token_expires_in() {
        return refresh_token_expires_in;
    }

    public void setRefresh_token_expires_in(String refresh_token_expires_in) {
        this.refresh_token_expires_in = refresh_token_expires_in;
    }
}
