package com.example.springmybatis.config.websocket;

import com.example.springmybatis.websocket.client.base.BaseHisDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author zhangchuansheng on 2018-12-24
 */
@Configuration
public class WebSocketConfig {

    @Value("${session-pool-size}")
    private Integer sessionPollSize;

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
    @Autowired
    public void setMessageService( ) {
        BaseHisDataService.sessionPollSize = sessionPollSize;
    }
}
