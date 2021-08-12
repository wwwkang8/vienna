package com.yogurt.vienna.controller;

import com.yogurt.vienna.entity.News.NewsDTO;
import com.yogurt.vienna.service.NewsScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsScraperController {

    @Autowired
    NewsScraperService newsScraperService;

    @RequestMapping("/news")
    public ResponseEntity<?> newsScraping(){

        List<NewsDTO> newsDTOList = new ArrayList<>();
        newsDTOList = newsScraperService.scrapingNews();

        return ResponseEntity.ok(newsDTOList);
    }

}
