package com.example.springmybatis.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class AsyncConfig {

    @Bean
    public AsyncTaskExecutor test(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.
        return scheduler;
    }
}
