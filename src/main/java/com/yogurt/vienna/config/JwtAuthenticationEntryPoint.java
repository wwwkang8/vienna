package com.yogurt.vienna.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /** 참고문서 : https://www.javainuse.com/spring/boot-jwt */

    /**
     * AuthenticationEntryPoint를 상속받아 메서드를 오버라이드
     * 이 클래스는 인증되지 않은 요청을 거절하고, 401코드로 응답해주는 역할을 한다.
     * */

    private static final long serialVersionUID = -7858869558953243875L;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
