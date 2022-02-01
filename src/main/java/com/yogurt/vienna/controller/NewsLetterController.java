package com.yogurt.vienna.controller;

import com.yogurt.vienna.dto.News.AptInfoDTO;
import com.yogurt.vienna.service.NewsLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@Controller
public class NewsLetterController {

    @Value("${SENDGRID_APIKEY}")
    private String sendGridApiKey;

    @Autowired
    NewsLetterService newsLetterService;

    @RequestMapping("/send/mail")
    public ResponseEntity<?> sendNewsLetter() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {

        /** 아래 주석친 부분이 메일 보내는 곳 */
        newsLetterService.sendNewsLetter(sendGridApiKey);
        List<AptInfoDTO> aptInfoDTOList = newsLetterService.getAptPrice();


        return ResponseEntity.ok("ok");

    }

}
