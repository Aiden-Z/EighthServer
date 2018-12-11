package com.eight.server.Message;

import com.eight.server.WebSocket.S2CSession;
import com.eight.server.WebSocket.SessionState;
import org.json.JSONObject;

public class MsgLogin extends MessageBase {
    @Override
    public void handleMessage(S2CSession session) {
        // todo 数据库校验用户登录信息
        // 假设成功
        JSONObject jsonObject = new JSONObject(this.getContent());
        String userid = jsonObject.getString("userid");
        System.out.println("user id: " + userid + " logged in");
        if (session.compareSessionState(SessionState.LogingIn)){
            session.loginSession(userid);
        }
        JSONObject temp = new JSONObject();
        temp.put("userid", userid);
        temp.put("result", true);
        MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
        session.sendMessage(msgLoginRsp.toJson());
    }
}
