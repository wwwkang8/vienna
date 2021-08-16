package com.yogurt.vienna.batch.jobs;

import com.yogurt.vienna.batch.tasklets.NewsTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    /** 참고문서 : https://url.kr/7ux6ib */

    private final JobBuilderFactory jobBuilderFactory; //Job 빌더 생성용
    private final StepBuilderFactory stepBuilderFactory; //step 빌더 생성용

    @Autowired
    public NewsTasklet newsTasklet;

    /** JobBuilderFactory 사용하여 NewsJob 생성 */
    /** 기억보단 기록을 참고자료 : https://jojoldu.tistory.com/325 */

    @Bean
    public Job sendNewsJob(){
        return jobBuilderFactory.get("sendNewsJob")
                .start(sendNewsStep()) //step 설정
                .build();
    }

    //stepBuilderFactory 사용하여 sendNewsStep 생성
    @Bean
    public Step sendNewsStep(){
        return stepBuilderFactory.get("sendNewsStep")
                    .tasklet(newsTasklet) //Tasklet 설정
                    .build();
    }

}
