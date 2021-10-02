package com.yogurt.vienna.dto.Jwt;

public class JwtResponse {

    /** 사용자에게 JWT 토큰을 응답하기 위해 필요한 객체 */

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken(){
        return this.jwtToken;
    }
}
