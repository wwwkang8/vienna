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

        /** kakao에서 리다이렉트한 요청은 토큰을 가지고 있지 않아서 오류 발생 */

        String authCode = code;

        //kakaoService.getAccessToken(authCode);

        /** 참고 자료 : https://www.youtube.com/watch?v=NwQ_55l0Za4 */

        /** Rest API 호출을 위한 헤더, 바디 생성 */
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", authCode);

        /** Http헤더, 바디를 하나의 객체로 만든다. */
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                                                        new HttpEntity<>(body, headers);

        /** 카카오로 POST 요청 쏘기 */
        ResponseEntity<String> response = restTemplate.exchange(
                redirectUri,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        System.out.println(response);

        return ResponseEntity.ok(response);


    }

    @RequestMapping("/kakaoLogin")
    public ResponseEntity<?> kakaoLogin(@RequestParam("access_token") String access_token){

        System.out.println("kakaoLogin 맵핑 - 토큰 : " + access_token);

        String token = access_token;
        KakaoLoginDTO kakaoLoginDTO = new KakaoLoginDTO();
        kakaoLoginDTO.setAccess_token(token);

        return ResponseEntity.ok(kakaoLoginDTO);

    }

}
