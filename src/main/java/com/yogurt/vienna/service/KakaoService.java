package com.yogurt.vienna.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
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

    private String result;

    /** HttpClient를 사용한 API POST 호출 */
    public String getAccessToken(String authCode){

        /** 참고 : https://github.com/JSBeatCode/kakaologin */

        String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        System.out.println("authCode = " + authCode);

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+clientId);
            sb.append("&redirect_uri="+redirectUri);
            sb.append("&code="+authCode);
            sb.append("&client_secret="+clientSecret);


            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("response code = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";
            while((line = br.readLine())!=null) {
                result += line;
            }


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("accessToken : "+accessToken);
            System.out.println("refreshToken : "+refreshToken);

            br.close();
            bw.close();


        }catch(Exception e){
            e.printStackTrace();
        }



        return accessToken;


    }

    /** RestTemplate 방식 POST 호출(버그 있음) */
//    public String getAccessToken(String authCode) {
//
//        String accessToken = "";
//        String refreshToken = "";
//        String requestUrl = "https://kauth.kakao.com/oauth/token";
//
//        try {
//            //헤더 설정
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//
//
//            //POST 요청할 파라미터 설정
//            Map<String, Object> map = new HashMap<>();
//            map.put("grant_type", "code");
//            map.put("client_id", client_id);
//            map.put("redirect_uri", "http://localhost:8080/kakaoLogin");
//            map.put("code", authCode);
//            String parameter = objectMapper.writeValueAsString(map);
//
//
//            //HttpEntity에 헤더 및 params 설정
//            HttpEntity httpEntity = new HttpEntity(parameter, httpHeaders);
//            System.out.println(httpEntity);
//
//            //RestTemplate의 exchange 메서드를 사용하여 URL에 요청
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<?> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, String.class);
//
//            //요청후 응답확인
//            System.out.println(responseEntity.getStatusCode());
//            System.out.println(responseEntity.getBody());
//
//            System.out.println("getAccessToken 함수 실행 완료");
//            //result = responseEntity.getBody().toString();
//
//
//        } catch (Exception e) {
//
//            System.out.println("카카오 토큰 발급 오류");
//            e.printStackTrace();
//
//        }
//
//
//        return result;
//    }

}

