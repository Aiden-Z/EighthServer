package com.eight.server.Message;

import com.eight.server.Database.entity.Test;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

import java.util.List;

public class MsgQueryTest extends MessageBase { // 用于查询某学生测试结果
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        String scno = jsonObject.getString("scaleNo");
        String sno = jsonObject.getString("studentNo");
        String cno = jsonObject.getString("consultantNo");
        Test test = new Test();
        test.setScno(scno);
        test.setSno(sno);
        test.setCno(cno);
        List<Test> tests = test.selectAll();
        JSONObject result = new JSONObject();
        result.put("scaleNo", scno);
        result.put("studentNo", sno);
        result.put("consultantNo", cno);
        result.put("tests", tests);

        MsgQueryTestRsp msgQueryTestRsp = new MsgQueryTestRsp(this.getId() + 1, result.toString());
        s2CSession.sendMessage(msgQueryTestRsp.toJson());
    }
}
