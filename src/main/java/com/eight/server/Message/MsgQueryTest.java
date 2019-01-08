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
        String scno = jsonObject.getString("scaleNo");
        Test test = new Test();
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(q->q.eq("sno", sno).eq("scno", scno));
        List<Test> tests = test.selectList(queryWrapper);
        JSONObject result = new JSONObject();
        result.put("studentNo", sno);
        result.put("tests", tests);

        MsgQueryTestRsp msgQueryTestRsp = new MsgQueryTestRsp(this.getId() + 1, result.toString());
        session.sendMessage(msgQueryTestRsp.toJson());
    }

    private void handleConsultant(JSONObject jsonObject, S2CSession session) {
        String consultantNo = jsonObject.getString("consultantNo");
        String scno = jsonObject.getString("scaleNo");
        Consult consult = new Consult();
        QueryWrapper<Consult> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(q->q.eq("cno", consultantNo).eq("scno", scno));
        List<Consult> consults = consult.selectList(queryWrapper); // 拿到该咨询师全部记录
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
