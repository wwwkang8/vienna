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

    @RequestMapping(value="/user/subscribe", method = RequestMethod.POST)
    public ModelAndView subscribe(HttpServletRequest httpServletRequest){

        String email = httpServletRequest.getParameter("email");

        String result = userService.subscribe(email);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/subscribeSuccess");
        mv.addObject("result", result);

        return mv;

    }









}
