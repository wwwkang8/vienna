package com.yogurt.vienna.service;

import com.yogurt.vienna.entity.NewsDTO;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsScraperService {

    public List<NewsDTO> scrapingNews(){

        Document news;
        List<NewsDTO> newsDTOList = new ArrayList<>();

        try{
            /** 매일경제 news 페이지 링크 연결 */
            news = Jsoup.connect("https://www.mk.co.kr/news/realestate/").get();

            /** article_list 클래스 하위의 tit 클래스 하위의 a 태그 스크래핑 */
            Elements newsLinks = news.select(".article_list > .tit > a");
            for(Element link : newsLinks){

                /** 스크래핑한 링크와 텍스트 생성하여 ArrayList에 추가 */
                NewsDTO newsDTO = new NewsDTO(link.attr("href"), link.text());
                newsDTOList.add(newsDTO);

            }

        }catch(Exception e){
            System.out.println("news 스크래핑 오류");
            e.printStackTrace();
        }

        return newsDTOList;
    }

}
