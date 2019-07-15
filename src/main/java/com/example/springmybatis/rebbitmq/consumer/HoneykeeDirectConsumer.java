package com.example.springmybatis.rebbitmq.consumer;

import com.example.springmybatis.rebbitmq.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

@Service
@Slf4j
public class HoneykeeDirectConsumer {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @RabbitListener(queues = "honeykee.news")
    public void honeykeeNewsListener(Person object){
        System.out.println( "person-->" + object.toString());
    }

//    @RabbitListener(queues = "honeykee.*")
    public void honeykeeOneListener(Message message){
        System.out.println( "body -->" + new String( message.getBody())  + " properties -->" + message.getMessageProperties().toString() );
    }

        @RabbitListener(queues = "topic.queue" )
    public void topicQueueOneListener(@Payload Message message){
        log.info("payload--> " + new String(message.getBody(), Charset.forName("UTF-8")));
//        System.out.println( "body -->" + new String( message.getBody())  + " properties -->" + message.getMessageProperties().toString() );
    }
}
