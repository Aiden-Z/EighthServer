package com.eight.server.Message;

import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

// 此为消息体基类，所有来自于客户端消息都需要继承自此消息
public class MessageBase{
    private String id; // 消息号
    private JSONObject content; // 消息体
    public void handleMessage(S2CSession s2CSession) { // 子类必须重载

    }

    @Override
    public String toString() {
        return "MessageBase id : " + id.toString();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public JSONObject getContent() {
        return content;
    }
}
