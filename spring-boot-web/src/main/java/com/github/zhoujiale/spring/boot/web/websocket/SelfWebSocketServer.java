package com.github.zhoujiale.spring.boot.web.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhou
 * @className SelfWebSocketServer
 * @descrption websocket节点
 * @date 2022/4/20 16:29
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{userId}")
public class SelfWebSocketServer {


    private static int onlineCount = 0;

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    private Session session;

    private Long userId;

    private static final String PING = "ping";

    private static final String PONG = "pong";


    public static synchronized Integer getOnlineCount(){
        return SelfWebSocketServer.onlineCount;
    }

    private static synchronized void addOnlineCount(){
        SelfWebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount(){
        SelfWebSocketServer.onlineCount--;
    }

    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Long userId){
        this.session = session;
        this.userId = userId;
        String user = String.valueOf(userId);
        if (sessionMap.containsKey(user)){
            sessionMap.put(user,session);
        }else {
            sessionMap.put(user,session);
            addOnlineCount();
        }
        log.info("socket add userId:[{}],online:[{}]",userId,onlineCount);
        //sendUserMessage("success", Collections.singletonList(userId));
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId){
        String user = String.valueOf(userId);
        if(sessionMap.containsKey(user)) {
            sessionMap.remove(user);
            subOnlineCount();
            log.info("socket remove userId:[{}],online:[{}]",user,onlineCount);
        }
    }

    @OnMessage
    public void onMessage(String message,@PathParam("userId")Long userId){
        String user = String.valueOf(userId);
        boolean containsKey = sessionMap.containsKey(user);
        if (containsKey){
            sendUserMessage(message.equals(PING)?PONG:message, Collections.singletonList(userId));
        }
    }


    public void sendUserMessage(String message, List<Long> userIdList){
        for (Long userId : userIdList) {
            String user = String.valueOf(userId);
            if (null != user && sessionMap.containsKey(user)){
                Session session = sessionMap.get(user);
                this.sendMessage(message,session);
                log.info("send websocket message,userId[{}],message[{}]",user,message);
            }else {
                log.warn("userId or websocket session null,user:[{}]",user);
            }
        }
    }

    public void sendMessage(String message, Session session){
        session.getAsyncRemote().sendText(message);
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        log.error("websocket error, userId {[]},error,{[]}",this.userId,throwable.getMessage());
        throwable.printStackTrace();
    }



}
