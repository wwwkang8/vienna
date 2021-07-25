package com.yogurt.vienna.controller;

import com.yogurt.vienna.entity.KakaoLoginDTO;
import com.yogurt.vienna.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoController {

    @Autowired
    KakaoService kakaoService;

    @RequestMapping("/kakaoAuth")
    public void kakaoAuthorizationCode(@RequestParam("code") String code){

        String authCode = code;

        kakaoService.getAccessToken(authCode);

    }

    @RequestMapping("/kakaoLogin")
    public ResponseEntity<?> kakaoLogin(@RequestParam("access_token") String access_token){

        String token = access_token;
        KakaoLoginDTO kakaoLoginDTO = new KakaoLoginDTO();
        kakaoLoginDTO.setAccess_token(token);

        System.out.println("kakaoLogin 맵핑 - 토큰 : " + access_token);

        return ResponseEntity.ok(kakaoLoginDTO);

    }

}
