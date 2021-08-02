package com.yogurt.vienna.controller;

import com.yogurt.vienna.entity.KakaoLoginDTO;
import com.yogurt.vienna.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @Value("${kakaoRestApiKey}")
    private String clientId;

    @Value("${redirectUri}")
    private String redirectUri;

    @Value("${clientSecret}")
    private String clientSecret;

    @RequestMapping("/kakaoAuth")
    public ResponseEntity<?> kakaoAuthorizationCode(@RequestParam("code") String code){

        String authCode = code;
        ResponseEntity<?> response = kakaoService.getAccessToken(authCode);

        return ResponseEntity.ok(response);
    }

}
