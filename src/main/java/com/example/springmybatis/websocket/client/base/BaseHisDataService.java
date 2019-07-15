package com.example.springmybatis.websocket.client.base;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zcs
 * @date   2019-3-7
 */
@Slf4j
@ClientEndpoint
public  class BaseHisDataService {

    URI uri ;

    Session session;

    @Value("${websocket.url}")
    String websocketUrl;

    WebSocketContainer container;

    private static int onlineCount = 0;

    public static Integer sessionPollSize;

    public  LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private static CopyOnWriteArrayList<BaseHisDataService> writeArrayList = new CopyOnWriteArrayList<>();

    //初始化websocket连接
//    @PostConstruct      // !!!!!!
    public void  startWebSocket() {
        try {
            uri =URI.create(websocketUrl);
            log.info("HisDataSendService uri created");
             container = ContainerProvider.getWebSocketContainer();
//            container.connectToServer(this, this.uri);
            session = container.connectToServer(BaseHisDataService.class, uri );
            System.out.println();
//            remoteEndpoint = session.getBasicRemote();  //放开
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @OnOpen
    public void open(Session session) {
        log.info( "online count -->" + getOnlineCount() + " ----onWriteArrayList.size -->  " + writeArrayList.size()  );

        //当websocket数量达到188的时候清理所有websocket
        //版本一 清空线程池
        if( writeArrayList.size() > sessionPollSize ){
            writeArrayList.forEach(hisDataSocket -> {
                try {
                    hisDataSocket.session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writeArrayList.clear();
            onlineCount = 0 ;
            log.info(" all session has been closed ---------------------------------");
        }

        log.info("HisDataSendService Client WebSocket start.");
        session.setMaxIdleTimeout( 365 * 24 * 60 * 60 * 1000 );
        this.session = session;
        writeArrayList.add(this);
        addOnlineCount();
//        session.getBasicRemote().sendText("客户端发送数据");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("++++++++++++++++++返回数据：" + message);
    }


    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info("Websocket closed .....................................");
        writeArrayList.remove(this);
        subOnlineCount();
    }

    @OnError
    public void onError(Session session, Throwable t) {
        //先关闭session
        try {
            this.session.close();
            session.close();
            log.error( " session close success !!!!!!!!!!!!!!!!!!!!!!!!!!" +  t.getMessage() );
        } catch (IOException e) {
            log.error("session close error !!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }

        try {
            this.session = container.connectToServer(BaseHisDataService.class, uri );
        } catch (DeploymentException e) {
            log.error("session reconnect err !!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t.printStackTrace();
        log.info(" session error end !!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public static synchronized int getOnlineCount() {
        return BaseHisDataService.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        BaseHisDataService.onlineCount++;
        log.info("add recent session count is --->" + onlineCount );
    }

    public static synchronized void subOnlineCount() {
        BaseHisDataService.onlineCount--;
        log.info("sub recent session count is --->" + onlineCount );
    }


    //推送算法 10条发一次
    @Async
    public void scheduleSend(){

        //上锁，保证session 不会同时发送多条数据，一条一条的发送
        synchronized (session) {
            //9条发送版本/websocket
            if (linkedBlockingQueue != null && linkedBlockingQueue.size() > 0) {
                try {

                    String result = linkedBlockingQueue.poll() ;
                    try {
    //                            session.sendMessage(new TextMessage(""));
    //                            session.getBasicRemote().sendText(result); //通过session 发送数据
                        session.getAsyncRemote().sendText(result);
                        log.info("session send message success --------------------------------------is open *** " + session.isOpen() );
                    } catch (IllegalStateException exception) {
                        try {
                            session.getAsyncRemote().sendText(result); //再发一次
                            log.info("session second ReSend message success !!! --------------------------------------");
                        } catch (IllegalStateException exception2) {
                            while ( !resendMessage( result ) ){ // 如果发送失败，继续发送
                                log.error(" ResendMessage again $$$-------------------------------------------");
                            }
                        }
                    }
                } catch ( Exception ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    private boolean resendMessage(String result ){
        log.info("session exception ************************ try reconnect ************************ is open ---" + session.isOpen() );
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            session = container.connectToServer(BaseHisDataService.class, uri);

            session.getAsyncRemote().sendText(result);
            log.info("session third ReSend message success ************************");

            return true;
        } catch (DeploymentException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}
