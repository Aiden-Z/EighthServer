package com.eight.server.Message;

import com.eight.server.PsychTest.PsychScale;
import com.eight.server.PsychTest.PsychScaleCache;
import com.eight.server.WebSocket.S2CSession;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;

public class MsgAddScale extends MessageBase {
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        PsychScale psychScale = null;
        try {
            psychScale = mapper.readValue(jsonObject.getString("scale"), PsychScale.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PsychScaleCache.getInstance().addPsychScale(psychScale);
        PsychScaleCache.getInstance().saveFile();
    }
}
