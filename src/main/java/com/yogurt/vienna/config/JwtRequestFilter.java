package com.yogurt.vienna.config;

import com.yogurt.vienna.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * Request 요청이 들어올 때마다 이 JwtRequestFilter가 실행
     * 역할 : 요청에 유효한 JWT token이 있는지 확인
     * */

    /** 참고문서 : https://www.javainuse.com/spring/boot-jwt */

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        //토큰 헤더 출력
        System.out.println("토큰 헤더 : " + requestTokenHeader);

        String username = null;
        String jwtToken = null;

        /** JWT 토큰은 Bearer token 이라는 문자열 폼 안에 있다
         * 그래서 Bearer를 제거하고 token 문자열만 추출 */
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);

            try{
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            }catch(IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");
            }catch(ExpiredJwtException e){
                System.out.println("JWT token has expired");
            }
        }else{
            logger.warn("JWT Token does not begin with Bearer String");
        }

        /** 토큰을 받았다면 validate 작업 시작 */
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            /** 토큰이 유효하다면, Spring Security를 사용하여 수동으로 authentication 전송 */
            if(jwtTokenUtil.validateToken(jwtToken, userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                /**
                 * 인증정보를 context에 세팅한 후, 해당 사용자가 인증되었다는 것을 확인.
                 * 그래서 context 정보는 Spring Security 설정정보에 성공적으로 전달됨.
                 * */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);


    }
}
