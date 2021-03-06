package com.eight.server.WebSocket;

import com.eight.server.Message.MessageBase;
import com.eight.server.Protocol.Protocol;
import com.eight.server.Scheduler.ScheduledItem;
import com.eight.server.Scheduler.Scheduler;
import org.json.JSONException;
import org.json.JSONObject;
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
    private String userSessionID;// 单用户不重复，多用户可重复，用于在用户多开时确定
    private SessionState sessionState = SessionState.Initializing;
    private static Map<String, List<S2CSession>> sessionPool = new ConcurrentHashMap<>(); // key是用户名，value是该用户名下所有的连接
    private static Map<String, UserState> userStatePool = new ConcurrentHashMap<>(); // 存放用户状态(这里可以做成一个单例)
    private static Map<String, S2CSession> tempSessionPool = new ConcurrentHashMap<>(); // 存放刚建立连接的会话，会话状态转为登录成功后放入sessionPool

    private void setUserState(String userid, UserState userState) {
        userStatePool.computeIfAbsent(userid, k -> userState);
    }


    private boolean isjson(String string){
        try {
            com.alibaba.fastjson.JSONObject jsonStr= com.alibaba.fastjson.JSONObject.parseObject(string);
            if (string.charAt(0) != '{') {
                return false;
            }
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private MessageBase str2DTO(String str) throws IOException, JSONException {
        if (!isjson(str)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(str);
        int id = jsonObject.getInt("id");
        JSONObject content = jsonObject.getJSONObject("content");

        Class objectType;
        Object o = null;
        try {
            objectType = Class.forName(Protocol.getInstance().get(Integer.toString(id)));
            if (objectType != null) {
                o = objectType.newInstance();
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        MessageBase DTO = (MessageBase)o;
        DTO.setId(id);
        DTO.setContent(content.toString());
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
        System.out.println("user id: " + session.getId() + " connected");
        this.session = session;
        this.userid = session.getId();// 先用session的id暂时代替用户id
        this.userSessionID = session.getId();// 将生成的ID填入sessionID用做辨别同用户的不同会话
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("user id: " + userid + " 's message has a error filed");
            return;
        }
        if (content != null) {
            try {
                content.handleMessage(this);
                System.out.println(content.toString());
            } catch (Exception e){
                e.printStackTrace();
                System.out.println(message + ": caused some error.");
            }

        }
    }

    @OnClose
    public void onClose(){
        if (sessionState == SessionState.Initializing || sessionState == SessionState.LogingIn) {
            tempSessionPool.remove(this);
            System.out.println("user id: " + userid + " session id: " + userSessionID + " had been removed");
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
