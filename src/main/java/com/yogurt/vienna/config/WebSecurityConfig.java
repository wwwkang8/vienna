package com.yogurt.vienna.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /* 스프링부트 Security는 기본적을 cross-site forgery를 방지하기 위한 옵션이 있다.
    * 그 옵션이 있으면 권한이 없는 API 요청은 거절된다. 그래서 그 옵션을 해제하는 용도
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

}