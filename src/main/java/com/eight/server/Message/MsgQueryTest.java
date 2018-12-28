package com.eight.server.Message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eight.server.Database.entity.Consult;
import com.eight.server.Database.entity.Test;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MsgQueryTest extends MessageBase { // 用于查询测试结果
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        String type = jsonObject.getString("type");
        if (type.equals("student")) {
            handleStudent(jsonObject, s2CSession);
        } else if (type.equals("consultant")) {
            handleConsultant(jsonObject, s2CSession);
        } else {
            JSONObject result = new JSONObject();
            result.put("result", false);

            MsgQueryTestRsp msgQueryTestRsp = new MsgQueryTestRsp(this.getId() + 1, result.toString());
            s2CSession.sendMessage(msgQueryTestRsp.toJson());
        }

    }

    private void handleStudent(JSONObject jsonObject, S2CSession session) {
        String sno = jsonObject.getString("studentNo");
        Test test = new Test();
        List<Test> tests = test.selectList(new QueryWrapper<Test>().eq("sno", sno));
        JSONObject result = new JSONObject();
        result.put("studentNo", sno);
        result.put("tests", tests);

        MsgQueryTestRsp msgQueryTestRsp = new MsgQueryTestRsp(this.getId() + 1, result.toString());
        session.sendMessage(msgQueryTestRsp.toJson());
    }

    private void handleConsultant(JSONObject jsonObject, S2CSession session) {
        String consultantNo = jsonObject.getString("consultantNo");
        Consult consult = new Consult();
        List<Consult> consults = consult.selectList(new QueryWrapper<Consult>().eq("cno", consultantNo)); // 拿到该咨询师全部记录
        Set<String> students = new HashSet<>();
        for (int i = 0; i < consults.size(); i++) {
            if (consults.get(i).getCno().equals(consultantNo)) {
                students.add(consults.get(i).getSno());
            }
        }
        Test test = new Test();
        List<Test> tests = test.selectList(new QueryWrapper<Test>().eq("cno", consultantNo));
        tests.removeIf(t->!students.contains(t.getSno()));
        JSONObject result = new JSONObject();
        result.put("consultantNo", consultantNo);
        result.put("tests", tests);

        MsgQueryTestRsp msgQueryTestRsp = new MsgQueryTestRsp(this.getId() + 1, result.toString());
        session.sendMessage(msgQueryTestRsp.toJson());
    }
}
