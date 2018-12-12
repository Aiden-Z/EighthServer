package com.eight.server.WebSocket;

import com.eight.server.Message.MessageBase;
import com.eight.server.Protocol.Protocol;
import com.eight.server.Scheduler.ScheduledItem;
import com.eight.server.Scheduler.Scheduler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket")
@Component
public class S2CSession {
    private Session session;
    private String userid;
    private int userSessionID;// 单用户不重复，多用户可重复，用于在用户多开时确定
    private SessionState sessionState = SessionState.Initializing;
    private static Map<String, List<S2CSession>> sessionPool = new ConcurrentHashMap<>(); // key是用户名，value是该用户名下所有的连接
    private static Map<String, UserState> userStatePool = new ConcurrentHashMap<>(); // 存放用户状态(这里可以做成一个单例)
    private static Map<String, S2CSession> tempSessionPool = new ConcurrentHashMap<>(); // 存放刚建立连接的会话，会话状态转为登录成功后放入sessionPool

    private void setUserState(String userid, UserState userState) {
        userStatePool.computeIfAbsent(userid, k -> userState);
    }

    @SuppressWarnings("unchecked")
    private MessageBase str2DTO(String str) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        MessageBase msg = mapper.readValue(str, MessageBase.class);
        Class objectType = null;
        try {
            objectType = Class.forName(Protocol.getInstance().get(msg.getId().toString()));
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        MessageBase DTO = null;
        if (objectType != null) {
            DTO = (MessageBase) mapper.readValue(str, objectType);
        }
        return DTO;
    }

    public void loginSession(String userid) { // 当登录消息校验完成后执行
        this.userid = userid;
        tempSessionPool.remove(session.getId());
        List<S2CSession> list;
        list = sessionPool.computeIfAbsent(userid, k-> Collections.synchronizedList(new LinkedList<>()));
        list.add(this);
        setUserState(userid, UserState.Online);
        this.sessionState = SessionState.Online;
    }

    public boolean compareSessionState(SessionState state) {
        return state == sessionState;
    }

    @OnOpen
    public void open (Session session) {
        System.out.println("userid : " + session.getId() + " connected");
        this.session = session;
        this.userid = session.getId();// 先用session的id暂时代替用户id
        this.userSessionID = Integer.parseInt(session.getId());// 将生成的ID填入sessionID用做辨别同用户的不同会话
        sessionState = SessionState.LogingIn;
        tempSessionPool.put(session.getId(), this); // 刚连接放入temp池
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
        MessageBase content = null;
        try {
            content = str2DTO(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content != null) {
            content.handleMessage(this);
            System.out.println(content.toString());
        }
    }

    @OnClose
    public void onClose(){
        if (sessionState == SessionState.Initializing || sessionState == SessionState.LogingIn) {
            tempSessionPool.remove(this);
        } else {
            List<S2CSession> sessions = sessionPool.get(userid);
            sessions.remove(this);
            System.out.println("user id: " + userid + " session id: " + userSessionID + " had been removed");
            sessionState = SessionState.Offline;
            if (sessions.size() == 0) { // 该用户与服务器不存在任意一个会话
                System.out.println("user id: " + userid + " had been put in scheduler");
                ScheduledItem item = new ScheduledItem(System.currentTimeMillis() + 5000, ()->{
                    boolean bool = sessionPool.get(userid).size() == 0;
                    if (bool) {
                        setUserState(userid, UserState.Offline);
                        System.out.println("user id: " + userid + " had been kicked out");
                    } else {
                        System.out.println("user id: " + userid + " hadn't been kicked out because he has a connecting connection");
                    }
                    return true;
                }); // 此处的5000后期可改为config读取
                Scheduler.getInstance().PushItem(item);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println(userid + "'s connection occurred some error. userSessionID: " + userSessionID);
        error.printStackTrace();
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
