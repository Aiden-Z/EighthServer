package com.eight.server.Message;


import com.eight.server.WebSocket.S2CSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

// 此为消息体基类，所有发送至客户端消息都需要继承自此消息
public class MessageRspBase {
    private Integer id; // 消息号
    private JSONObject content; // 消息体

    public MessageRspBase(Integer id, JSONObject content) {
        this.id = id;
        this.content = content;
    }

    public String toJson(){
        return content.toString();
    }

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

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public JSONObject getContent() {
        return content;
    }
}
