package com.eight.server.Message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eight.server.Database.entity.Examine;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MsgRequestExmaine extends MessageBase {
    @Override
    public void handleMessage(S2CSession s2CSession) throws JSONException {
        JSONObject jsonObject = new JSONObject(getContent());
        String cno = jsonObject.getString("consultantNo");
        List<Examine> examines = new Examine().selectList(new QueryWrapper<Examine>().eq("cno", cno));
        JSONObject result = new JSONObject();
        result.put("consultantNo", cno);
        result.put("examines", examines);
        MsgRequestExamineRsp msgRequestExamineRsp = new MsgRequestExamineRsp(getId() + 1, result.toString());
        s2CSession.sendMessage(msgRequestExamineRsp.toJson());
    }
}
