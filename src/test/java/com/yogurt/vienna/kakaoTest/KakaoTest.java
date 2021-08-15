package com.yogurt.vienna.kakaoTest;

import com.yogurt.vienna.config.WebSecurityConfig;
import com.yogurt.vienna.controller.KakaoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KakaoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WebSecurityConfig.class);

    @Test
    @DisplayName("카카오빈 조회")
    public void findKakaoBean(){

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);

            System.out.println("name = " + beanDefinitionName + " bean = " + bean);
        }

    }

}
