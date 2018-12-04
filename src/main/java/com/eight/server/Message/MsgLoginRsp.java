package com.eight.server.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

public class MsgLoginRsp extends MessageRspBase{
    public MsgLoginRsp(int id, String content) {
        this.setId(id);
        this.setContent(content);
    }
}
