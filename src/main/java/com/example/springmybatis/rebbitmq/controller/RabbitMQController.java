package com.example.springmybatis.rebbitmq.controller;

import com.example.springmybatis.rebbitmq.entity.Person;
import com.example.springmybatis.rebbitmq.producer.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    RabbitMQService rabbitMQService;

    @GetMapping(value = "/rabbit") //,produces = MediaType.APPLICATION_JSON_VALUE
    public Person testRabbitMQ(){
        rabbitMQService.SendMessage();
        return new Person(123,"zsc");
    }

}
