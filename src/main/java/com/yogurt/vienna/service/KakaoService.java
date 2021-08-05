package com.yogurt.vienna.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yogurt.vienna.entity.KakaoMessageDTO;
import com.yogurt.vienna.entity.NewsDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KakaoService {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${kakaoRestApiKey}")
    private String clientId;

    @Value("${redirectUri}")
    private String redirectUri;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${kakaoAccessToken}")
    private String kakaoAccessToken;

    private String result;

    /** HttpClient를 사용한 API POST 호출 */
    public ResponseEntity<?> getAccessToken(String authCode){

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
        body.add("scope", "talk_message"); // 내가 해당 사용자에게 필요한 동의항목(메시지 전송)

        /** Http헤더, 바디를 하나의 객체로 만든다. */
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);

        /** HTTP방식으로 카카오로 POST 요청 쏘기 - 그리고 response 받는다 */
        /**
         * 삽질 이유 : uri를 설정하는 곳에 카카오로 POST요청 보내는 URL을 세팅해야 하는데
         * redirectUri를 세팅을 해버려서 POST 요청이 계속해서 무한루프로 /kakaoAuth를 콜하게 되었다.
         */
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );


        return response;

    }

    public ResponseEntity<?> sendMessage(List<NewsDTO> newsDTOList){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+kakaoAccessToken);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        KakaoMessageDTO kakaoMessage = new KakaoMessageDTO();
        kakaoMessage.setObject_type("text");
        kakaoMessage.setText(newsDTOList.get(0).getText());
        kakaoMessage.setWeb_url(newsDTOList.get(0).getNewsLink());
        kakaoMessage.setButton_title("뉴스 읽기");

        Gson gson = new Gson();
        String jsonString = gson.toJson(kakaoMessage);
        System.out.println(jsonString);

        body.add("template_object", kakaoMessage.toString());

        /** Http헤더, 바디를 하나의 객체로 만든다. */
        HttpEntity<MultiValueMap<String, String>> kakaoMessageRequest =
                new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/api/talk/memo/default/send",
                HttpMethod.POST,
                kakaoMessageRequest,
                String.class
        );


        return response;

    }

}

