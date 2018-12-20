package com.eight.server.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MsgRegisterRsp extends MessageRspBase {
    public MsgRegisterRsp(int id, String content) {
        super(id, content);
    }
}
