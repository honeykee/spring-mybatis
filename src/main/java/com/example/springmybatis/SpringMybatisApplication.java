package com.example.springmybatis;

import com.example.springmybatis.config.MyProps;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
//@EnableConfigurationProperties
@EnableScheduling
@EnableRabbit
//@EnableJpaRepositories
public class SpringMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringMybatisApplication.class, args);
//        BIDataSourceProperties properties=context.getBean(BIDataSourceProperties.class);
//        System.out.println(properties);

        MyProps myProps = context.getBean(MyProps.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try{
//            System.out.println("simpleProp: " + myProps.getSimpleProp());
//            System.out.println("arrayProps: " + objectMapper.writeValueAsString(myProps.getArrayProps()));
//            System.out.println("listProp1: " + objectMapper.writeValueAsString(myProps.getListProp1()));
//            System.out.println("listProp2: " + objectMapper.writeValueAsString(myProps.getListProp2()));
//            System.out.println("mapProps: " + objectMapper.writeValueAsString(myProps.getMapProps()));

        }catch (Exception e){

        }


    }


}
