package com.eight.server.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class MsgRegisterRsp extends MessageRspBase {
    public MsgRegisterRsp(int id, JSONObject content) {
        super(id, content);
    }
}
