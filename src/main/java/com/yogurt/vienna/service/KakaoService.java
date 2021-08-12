package com.yogurt.vienna.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yogurt.vienna.entity.Kakao.ContentDTO;
import com.yogurt.vienna.entity.Kakao.KakaoMessageButtonDTO;
import com.yogurt.vienna.entity.Kakao.KakaoMessageDTO;
import com.yogurt.vienna.entity.News.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

        System.out.println(newsDTOList.get(0).getText());
        System.out.println(newsDTOList.get(1).getText());
        System.out.println(newsDTOList.get(2).getText());
        System.out.println(newsDTOList.get(3).getText());
        System.out.println(newsDTOList.get(4).getText());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer "+kakaoAccessToken);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        /** 카카오 리스트메시지 header, body 조립 */
        KakaoMessageDTO kakaoMessage = new KakaoMessageDTO();
        kakaoMessage.setObject_type("list");
        kakaoMessage.setHeader_title("DAILY NEWS");

        Map<String, String> header_link = new HashMap<>();
        header_link.put("web_url", "https://www.mk.co.kr");
        header_link.put("mobile_web_url", "https://www.mk.co.kr");
        header_link.put("android_execution_params", "main");
        header_link.put("ios_execution_params", "main");
        kakaoMessage.setHeader_link(header_link);

        /** 부동산뉴스 컨텐츠 조립(제목, 연결링크) */
        List<ContentDTO> contentList = new ArrayList<>();
        Map<String, String> newsLink = new HashMap<>();
        for(int i=0; i<newsDTOList.size(); i++){
            ContentDTO content = new ContentDTO();
            content.setTitle(newsDTOList.get(i).getText());
            content.setDescription("뉴스");
            content.setImage_url("");
            content.setImage_width("");
            content.setImage_height("");

            newsLink.put("web_url", newsDTOList.get(i).getNewsLink());
            newsLink.put("mobile_web_url", newsDTOList.get(i).getNewsLink());
            newsLink.put("android_execution_params", "");
            newsLink.put("ios_execution_params", "");
            content.setLink(newsLink);

            contentList.add(content);
        }
        kakaoMessage.setContents(contentList);

        /** 버튼 세팅 */
        List<KakaoMessageButtonDTO> buttonList = new ArrayList<>();
        KakaoMessageButtonDTO webButton = new KakaoMessageButtonDTO();
        KakaoMessageButtonDTO appButton = new KakaoMessageButtonDTO();
        Map<String, String> webButtonLinks = new HashMap<>();
        Map<String, String> appButtonLinks = new HashMap<>();

        webButton.setTitle("웹으로 이동");
        webButtonLinks.put("web_url", "https://www.mk.co.kr/");
        webButtonLinks.put("mobile_web_url", "https://www.mk.co.kr/");
        webButton.setLink(webButtonLinks);

        appButton.setTitle("앱으로 이동");
        appButtonLinks.put("android_execution_params", "main");
        appButtonLinks.put("ios_execution_params", "main");
        appButton.setLink(appButtonLinks);
        buttonList.add(webButton);
        buttonList.add(appButton);

        kakaoMessage.setButtons(buttonList);

        /** Json으로 파싱 */
        Gson gson = new Gson();
        String kakaoMessageJson = gson.toJson(kakaoMessage);
        System.out.println(kakaoMessageJson);

        body.add("template_object", kakaoMessageJson);


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

