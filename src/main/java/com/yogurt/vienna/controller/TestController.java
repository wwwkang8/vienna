package com.yogurt.vienna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test() throws Exception{
        System.out.println("[KJH] testcontroller");

        ModelAndView mv = new ModelAndView("test");
        mv.addObject("name", "helloeldodld");

        List<String> testList = new ArrayList<String>();
        testList.add("a");
        testList.add("b");
        testList.add("c");

        mv.addObject("list", testList);

        return mv;

    }

}
