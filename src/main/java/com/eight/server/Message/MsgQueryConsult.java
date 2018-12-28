package com.eight.server.Message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eight.server.Database.entity.Consult;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

import java.util.List;

/**
 * 用于请求咨询，查看自己当前已有的咨询
 */
public class MsgQueryConsult extends MessageBase {
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        String type = jsonObject.getString("type");
        QueryWrapper<Consult> wrapper = null;
        if (type.equals("student")) {
            wrapper = new QueryWrapper<Consult>().eq("sno", jsonObject.getString("studentNo"));
        }else if (type.equals("consultant")){
            wrapper = new QueryWrapper<Consult>().eq("cno", jsonObject.getString("consultantNo"));
        }else {
            JSONObject result = new JSONObject();
            result.put("result", false);
            MsgQueryConsultRsp msgQueryConsultRsp = new MsgQueryConsultRsp(getId() + 1, result.toString());
            s2CSession.sendMessage(msgQueryConsultRsp.toJson());
            return;
        }
        List<Consult> consults = new Consult().selectList(wrapper);
        JSONObject result = new JSONObject();
        result.put("result", true);
        result.put("consults", consults);
        MsgQueryConsultRsp msgQueryConsultRsp = new MsgQueryConsultRsp(getId() + 1, result.toString());
        s2CSession.sendMessage(msgQueryConsultRsp.toJson());
    }
}
