package com.example.springmybatis;

import com.example.springmybatis.config.MyProps;
import com.example.springmybatis.rebbitmq.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringMybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MyProps myProps;

    @Test
    public void propsTest() throws JsonProcessingException {
        System.out.println("simple: " + myProps.getSimple());
        System.out.println("simpleProp: " + myProps.getSimpleProp());
        System.out.println("arrayProps: " + objectMapper.writeValueAsString(myProps.getArrayProps()));
        System.out.println("listProp1: " + objectMapper.writeValueAsString(myProps.getListProp1()));
        System.out.println("listProp2: " + objectMapper.writeValueAsString(myProps.getListProp2()));
        System.out.println("mapProps: " + objectMapper.writeValueAsString(myProps.getMapProps()));
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbit (){
//        rabbitTemplate.convertAndSend(new Person(123,"AAA"));
        rabbitTemplate.convertAndSend("topic.exchange","topic.route",new Person(225,"DDD"));
//        rabbitTemplate.convertAndSend("ABCCCC");
        // Object object = rabbitTemplate.receiveAndConvert("honeykee.*");
        //System.out.println( "object:" + object.toString());

        ObjectMapper mapper=new ObjectMapper();
    }

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void testAmqpAdmin(){
        amqpAdmin.declareExchange(new TopicExchange("topic.exchange"));
        amqpAdmin.declareQueue(new Queue("topic.queue"));
        amqpAdmin.declareBinding(new Binding("topic.queue", Binding.DestinationType.QUEUE,"topic.exchange","topic.route",null));
    }

}
