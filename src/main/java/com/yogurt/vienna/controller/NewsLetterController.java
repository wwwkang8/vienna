package com.yogurt.vienna.controller;

import com.yogurt.vienna.service.NewsLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class NewsLetterController {

    @Value("${SENDGRID_APIKEY}")
    private String sendGridApiKey;

    @Autowired
    NewsLetterService newsLetterService;

    @RequestMapping("/send/mail")
    public ResponseEntity<?> sendNewsLetter() throws IOException {

        //newsLetterService.sendNewsLetter(sendGridApiKey);
        StringBuilder stringBuilderResponse = newsLetterService.getAptPrice();
        System.out.println(stringBuilderResponse);

        return ResponseEntity.ok("ok");

    }

}
