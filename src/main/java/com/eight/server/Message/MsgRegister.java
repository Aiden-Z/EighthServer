package com.eight.server.Message;

import com.eight.server.WebSocket.S2CSession;
import com.eight.server.WebSocket.SessionState;
import org.json.JSONObject;

public class MsgRegister extends MessageBase{
    @Override
    public void handleMessage(S2CSession session) {
        // todo 和数据库交互确认能否注册
        // 假设注册成功
        JSONObject jsonObject = new JSONObject(this.getContent());
        String userid = jsonObject.getString("userid");

        JSONObject temp = new JSONObject();
        temp.put("userid", userid);
        temp.put("result", true);
        MsgRegisterRsp msgRegisterRsp = new MsgRegisterRsp(this.getId() + 1, temp.toString());
        session.sendMessage(msgRegisterRsp.toJson());
    }
}
