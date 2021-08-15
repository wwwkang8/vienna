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

    /** 10초마다 실행 */
    @Scheduled(fixedDelay = 60 * 1000L)
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
