package com.yogurt.vienna.newsScrapingTest;

import com.yogurt.vienna.config.JwtAuthenticationEntryPoint;
import com.yogurt.vienna.config.WebSecurityConfig;
import io.jsonwebtoken.Jwt;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NewsScrapingTest {

    /** Annotation기반 스프링컨테이너 생성 */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WebSecurityConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findByName(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);

            System.out.println("name = " + beanDefinitionName + " bean = " + bean);
        }

    }





}
