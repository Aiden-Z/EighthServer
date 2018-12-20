package com.eight.server.Message;

import com.eight.server.PsychTest.PsychScale;
import com.eight.server.PsychTest.PsychScaleCache;
import com.eight.server.WebSocket.S2CSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class MsgRequestScale extends MessageBase { // 用于客户端请求量表
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(this.getContent());
        String id = jsonObject.getString("id");
        PsychScale psychScale = PsychScaleCache.getInstance().getPsychScale(id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String scale = null;
        try {
            scale = mapper.writeValueAsString(psychScale);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        MsgRequestScaleRsp msgRequestScaleRsp = new MsgRequestScaleRsp(getId() + 1, scale);
        s2CSession.sendMessage(msgRequestScaleRsp.toJson());
    }
}
