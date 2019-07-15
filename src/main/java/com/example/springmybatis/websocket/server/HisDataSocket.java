package com.example.springmybatis.websocket.server;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Cambridge.w
 * Date:2019/2/19
 * Time:00:10
 * Description:This class describes
 */
@ServerEndpoint(value = "/hisData")
@Component
@Log4j2
@Order(1)
public class HisDataSocket {

    @Autowired

    private static int onlineCount = 0;     //number of client online

    private static CopyOnWriteArrayList<HisDataSocket> onWriteArrayList = new CopyOnWriteArrayList<>();

    private Session session;




    @OnOpen
    public void onOpen (Session session) {
//        session.isOpen();
        //当websocket数量达到188的时候清理所有websocket

//        if(getOnlineCount() > 188 ){
////            onWriteArrayList.remove()
//        }

        this.session = session;
        onWriteArrayList.add(this);
        addOnlineCount();
//        accessTokenResultStr = HttpxUtils.httpsRequest(ACCESS_TOKEN_URL, "GET", "");
        log.info(session.getId() + " session connected, current online count is " + getOnlineCount());
    }

    @OnClose
    public void onClose () {
        onWriteArrayList.remove(this);
        subOnlineCount();
        log.info(session.getId() + " session disconnected, current online count is " + getOnlineCount());
    }

    @OnMessage
    public void onMessage (String message, Session session)  {
        //单条版本
//        mqService.sendToMQTT(message);
        //多条版本
//        asyncMQService.sendToMQTT(message);

        try {
            session.getBasicRemote().sendText(" ！ received data ... ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError (Session session, Throwable throwable) {
        log.info(session.getId() + " session error, current online count is " + getOnlineCount());
        log.info(throwable.getMessage());
    }

    private static synchronized int getOnlineCount () {
        return HisDataSocket.onlineCount;
    }

    private static synchronized void addOnlineCount () {
        HisDataSocket.onlineCount++;
    }

    private static synchronized void subOnlineCount () {
        HisDataSocket.onlineCount--;
    }

}
