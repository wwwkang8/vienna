package com.yogurt.vienna.batch.tasklets;

import com.yogurt.vienna.controller.KakaoController;
import com.yogurt.vienna.service.KakaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
public class NewsTasklet implements Tasklet {

    /** 참고문서 : https://url.kr/7ux6ib */

//    @Autowired
//    @Qualifier("kakaoController")
//    KakaoController kakaoController;

    private KakaoController kakaoController;

    public NewsTasklet (KakaoController kakaoController){
        this.kakaoController = kakaoController;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception{

        kakaoController.sendMessage();

        return RepeatStatus.FINISHED;
    }

}
