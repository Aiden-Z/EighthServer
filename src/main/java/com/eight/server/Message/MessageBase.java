package com.eight.server.Message;

import com.eight.server.WebSocket.S2CSession;
import org.json.JSONException;

// 此为消息体基类，所有来自于客户端消息都需要继承自此消息
public abstract class MessageBase{
    private Integer id; // 消息号
    private String content; // 消息体
    public abstract void handleMessage(S2CSession s2CSession) throws JSONException;

    @Override
    public String toString() {
        return "MessageBase id : " + id.toString();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
