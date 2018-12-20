package com.eight.server.Message;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eight.server.Database.entity.Student;
import com.eight.server.Database.entity.Test;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

import java.util.Date;

public class MsgScaleResult extends MessageBase {// 填写量表结果
    private static final String[] classes = {"普通","严重","障碍","高危"};
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        String sno = jsonObject.getString("studentNo");
        int score = jsonObject.getInt("score");
        Test test = new Test();
        test.setScno(jsonObject.getString("scaleNo"));
        test.setCno(jsonObject.getString("consultantNo"));
        test.setSno(sno);
        test.setTno(jsonObject.getString("testNo"));
        test.setTscore(score);
        test.setTresult(jsonObject.getString("testResult"));
        test.setTtime(new Date());
        test.insertOrUpdate();
        Student student = new Student();
        student.setSno(sno);
        student = student.selectOne(new EntityWrapper<Student>().eq("sname",sno));
        student.setSclass(classes[100 / score]);
        JSONObject result = new JSONObject();
        result.put("result", true);
        MsgScaleResultRsp msgScaleResultRsp = new MsgScaleResultRsp(getId() + 1, result.toString());
        s2CSession.sendMessage(msgScaleResultRsp.toJson());
    }
}
