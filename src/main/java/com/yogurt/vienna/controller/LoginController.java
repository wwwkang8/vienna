package com.yogurt.vienna.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//    @GetMapping("/login")
//    public LoginInfo getToken(){
//
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SecurityConfig.class);
//
//        LoginInfo loginInfo = new LoginInfo();
//
//
//        return loginInfo;
//    }

    @RequestMapping("/hello")
    public String firstPage(){
        return "helloWorld";
    }

}
