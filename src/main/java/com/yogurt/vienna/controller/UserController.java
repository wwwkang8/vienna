package com.yogurt.vienna.controller;

import com.yogurt.vienna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class UserController {

    Logger logger = Logger.getLogger("com.yogurt.vienna.controller.UserController");

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public RedirectView register(HttpServletRequest httpServletRequest){

        System.out.printf(httpServletRequest.toString());
        String email = httpServletRequest.getParameter("email");
        String pwd = httpServletRequest.getParameter("password");

        logger.info("이메일 : "+ email + ", 비밀번호 : "+pwd);

        String result = userService.register(email, pwd);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/");
        return redirectView;

    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView userRegister(HttpServletRequest httpServletRequest) throws Exception {
        ModelAndView mv = new ModelAndView("/user/userRegister");

        return mv;
    }





}
