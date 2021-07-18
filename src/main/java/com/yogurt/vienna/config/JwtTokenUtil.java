package com.yogurt.vienna.config;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenUtil implements Serializable {

    /** 참고문서 : https://www.javainuse.com/spring/boot-jwt */

    private static final long serialVersionUID = -2550185165626007488L;

    // 토큰 지속시
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    //application.properties에 설정된 secret 코
    @Value("${jwt.secret}")
    private String secret;

    //jwt 토큰에서 username 추출
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //jwt 토큰에서 DATE 정보 추출
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 토큰에서 정보를 추출하려면 secret 코드가 필요함.
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //토큰이 만료되었는지 체크
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //user에게 토큰 생성
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * 토큰 생성단계
     * 1. 토큰 정의 : 발행자, 만료일자, ID, Subject 등
     * 2. 알고리즘 및 비밀키 : HS512 알고리즘과 시크릿키 사용
     * 3. JWS Serialization : JWS Compact Serializationd을 사용하여 JWT에서 URL-safe 문자열로 변환
     * */
    private String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /** 토큰 인증 */
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()));
    }

}
