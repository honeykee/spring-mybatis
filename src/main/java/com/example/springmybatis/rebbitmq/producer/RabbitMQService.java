package com.example.springmybatis.rebbitmq.producer;


import com.example.springmybatis.rebbitmq.entity.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.UUID;

@Service
public class RabbitMQService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void SendMessage(){
        String msgId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend("topic.exchange","topic.route",new Person(225,"DDD"),new CorrelationData(msgId));
    }
    public void SendMessage2( Object obj){
        String msgId = UUID.randomUUID().toString();
//        Message message = new Message(new Person(155,"zcc").toString().getBytes(Charset.forName("UTF-8")),new MessageProperties() );

        Message message1 = MessageBuilder.withBody(obj.toString().getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setCorrelationId(msgId ).build();

        Person person = new Person(139,"LLL");
        Message message2 = MessageBuilder.withBody( person.toString().getBytes()).setCorrelationIdIfAbsent(msgId).build();
        rabbitTemplate.convertAndSend("topic.exchange","topic.route",message2,new CorrelationData(msgId));
        rabbitTemplate.convertAndSend("topic.exchange","topic.route",new Person(225,"DDD"),new CorrelationData(msgId));
    }

}
