package com.yogurt.vienna.controller;

import com.yogurt.vienna.entity.LoginInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public LoginInfo getToken(){

        LoginInfo loginInfo = new LoginInfo();


        return loginInfo;
    }

}
