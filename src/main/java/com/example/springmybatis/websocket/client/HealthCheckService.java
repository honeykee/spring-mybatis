package com.example.springmybatis.websocket.client;

import com.example.springmybatis.websocket.client.base.BaseHisDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "healthCheckService")
public class HealthCheckService extends BaseHisDataService {


    @Async
//    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleSender( ){
//        log.info( "HealthCheck    queue size --> " + this.linkedBlockingQueue.size() );
        scheduleSend();
    }


    @Async
//    @Scheduled(cron = "0/20 * * * * ?")
    public void scheduleAdder( ){
//        log.info( "Appointment    queue size --> " + this.linkedBlockingQueue.size() );
        this.linkedBlockingQueue.add( "Random  two--> " + Math.random() * 1000  );
    }

}
