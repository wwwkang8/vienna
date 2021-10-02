package com.yogurt.vienna.controller;

import com.yogurt.vienna.batch.tasklets.NewsTasklet;
import com.yogurt.vienna.dto.News.NewsDTO;
import com.yogurt.vienna.service.KakaoService;
import com.yogurt.vienna.service.NewsScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @Autowired
    NewsScraperService newsScraperService;

    @Value("${kakaoRestApiKey}")
    private String clientId;

    @Value("${redirectUri}")
    private String redirectUri;

    @Value("${clientSecret}")
    private String clientSecret;

    @Bean
    public NewsTasklet newsTasklet(KakaoController kakaoController){
        return new NewsTasklet(kakaoController);
    }

    @RequestMapping("/kakaoAuth")
    public ResponseEntity<?> kakaoAuthorizationCode(@RequestParam("code") String code){

        String authCode = code;
        ResponseEntity<?> response = kakaoService.getAccessToken(authCode);

        return ResponseEntity.ok(response);
    }

    @RequestMapping("/send/message")
    public ResponseEntity<?> sendMessage(){

        List<NewsDTO> newsDTOList = new ArrayList<>();
        newsDTOList = newsScraperService.scrapingNews();

        ResponseEntity<?> response = kakaoService.sendMessage(newsDTOList);

        return ResponseEntity.ok(response);

    }

}
