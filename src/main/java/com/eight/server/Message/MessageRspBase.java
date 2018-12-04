package com.eight.server.Message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

// 此为消息体基类，所有发送至客户端消息都需要继承自此消息
public class MessageRspBase {
    private Integer id; // 消息号
    private String content; // 消息体
    public String toJson(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String temp= null;
        try {
            temp = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return temp;
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

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
