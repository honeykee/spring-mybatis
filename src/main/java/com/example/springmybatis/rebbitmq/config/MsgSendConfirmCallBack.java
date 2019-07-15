package com.example.springmybatis.rebbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息发送回调:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
        log.info("MsgSendConfirmCallBack  , 回调的 id: " + correlationData );
        if (ack) {
            log.info("消息接收成功");
        } else {
            log.info("消息接收失败 + cause : " + cause );
        }
    }
}
