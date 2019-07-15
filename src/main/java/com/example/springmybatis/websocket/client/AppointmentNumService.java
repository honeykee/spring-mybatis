package com.example.springmybatis.websocket.client;

import com.example.springmybatis.websocket.client.base.BaseHisDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service(value = "appointmentNumService")
public class AppointmentNumService extends BaseHisDataService {

    @Async
//    @Scheduled(cron = "0/1 * * * * ?")
    public void scheduleSender( ){
//        log.info( "Appointment    queue size --> " + this.linkedBlockingQueue.size() );
        scheduleSend();
    }

    @Async
//    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduleAdder( ){
//        log.info( "Appointment    queue size --> " + this.linkedBlockingQueue.size() );
        this.linkedBlockingQueue.add( "Random one--> " + Math.random() * 1000  );
    }


}
