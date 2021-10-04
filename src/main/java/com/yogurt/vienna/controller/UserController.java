package com.yogurt.vienna.controller;

import com.yogurt.vienna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(HttpServletRequest httpServletRequest){

        System.out.printf(httpServletRequest.toString());
        String email = httpServletRequest.getParameter("email");
        String pwd = httpServletRequest.getParameter("password");

        System.out.println("이메일 : "+ email + ", 비밀번호 : "+pwd);

        String result = userService.register(email, pwd);

        return ResponseEntity.ok("회원가입 완료!!" + " 아이디 : "+email);
    }



}
