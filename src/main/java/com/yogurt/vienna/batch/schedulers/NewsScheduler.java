package com.yogurt.vienna.batch.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class NewsScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;

    /** 스케쥴러 설정 참고자료 : https://huskdoll.tistory.com/819 */

    /** 매일 아침 08시 30분에 뉴스전송 배치작업 실행 */
    @Scheduled(cron = "0 33 16 ? * *")
    public void executeJob(){
        try{
            jobLauncher.run(
                    job,
                    new JobParametersBuilder()
                            .addString("datetime", LocalTime.now().toString())
                    .toJobParameters()
            );
        }catch(JobExecutionException e){
            System.out.println("배치작업 수행 오류");
            e.printStackTrace();
        }
    }

}
