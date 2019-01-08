package com.eight.server.Message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eight.server.Database.entity.Examine;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class MsgExamineResult extends MessageBase {
    @Override
    public void handleMessage(S2CSession s2CSession) throws JSONException {
        JSONObject jsonObject = new JSONObject(getContent());
        String type = jsonObject.getString("type");
        if (type.equals("instructor")) {
            handleInstructor(jsonObject, s2CSession);
        } else if (type.equals("consultant")){
            handleConsultant(jsonObject, s2CSession);
        }
    }

    private void handleConsultant(JSONObject jsonObject, S2CSession session) {
        LocalDateTime date = LocalDateTime.now();
        String sno = jsonObject.getString("studentNo");
        String feedback = jsonObject.getString("feedbackResult");
        String eno = jsonObject.getString("examineNo");
        Examine examine = new Examine();
        examine = examine.selectOne(new QueryWrapper<Examine>().eq("eno", eno));
        if (examine != null) {
            examine.setFeedbackresult(feedback);
            examine.setFeedbacktime(date);
            examine.update(new QueryWrapper<Examine>().eq("eno", eno));
            JSONObject result = new JSONObject();
            result.put("studentNo", sno);
            result.put("result", true);
            MsgExamineResultRsp msgExamineResultRsp = new MsgExamineResultRsp(getId() + 1, result.toString());
            session.sendMessage(msgExamineResultRsp.toJson());
        }
    }

    private void handleInstructor(JSONObject jsonObject, S2CSession session) {
        LocalDateTime date = LocalDateTime.now();
        String sno = jsonObject.getString("studentNo");
        String cno = jsonObject.getString("consultantNo");
        String ino = jsonObject.getString("instructorNo");
        String eDept = jsonObject.getString("dept");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        stringBuilder.append(sno);
        String eno = stringBuilder.toString();
        if (stringBuilder.length() > 14) {
            eno = stringBuilder.substring(0, 14);
        }

        Examine examine = new Examine();
        examine.setEno(eno);
        examine.setSno(sno);
        examine.setCno(cno);
        examine.setIno(ino);
        examine.setEdept(eDept);
        examine.setEtime(date);
        examine.setEresult("empty");
        examine.setFeedbackresult("empty");
        examine.insert();
        JSONObject result = new JSONObject();
        result.put("studentNo", sno);
        result.put("result", true);
        MsgExamineResultRsp msgExamineResultRsp = new MsgExamineResultRsp(getId() + 1, result.toString());
        session.sendMessage(msgExamineResultRsp.toJson());
    }
}
