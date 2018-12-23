package com.eight.server.Message;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eight.server.Database.entity.Student;
import com.eight.server.Database.entity.Test;
import com.eight.server.WebSocket.S2CSession;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgScaleResult extends MessageBase {// 填写量表结果
    private static final String[] classes = {"普通","严重","障碍","高危"};
    @Override
    public void handleMessage(S2CSession s2CSession) {
        Date date = new Date();
        JSONObject jsonObject = new JSONObject(getContent());
        String sno = jsonObject.getString("studentNo");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Long.toString(date.getTime()));
        stringBuilder.append(sno);
        String testNo = stringBuilder.toString();
        if (stringBuilder.length() > 14) {
            testNo = stringBuilder.substring(0, 14);
        }
        List<Object> tempArr = jsonObject.getJSONArray("sum").toList();
        List<Integer> sumArr = new ArrayList<>();
        for (int i = 0; i < tempArr.size(); i++) {
            sumArr.add((Integer) tempArr.get(i));
        }
        String testResult = StringUtils.join(sumArr, ',');
        int score = jsonObject.getInt("score");
        Test test = new Test();
        test.setScno(jsonObject.getString("scaleNo"));
        test.setCno(jsonObject.getString("consultantNo"));
        test.setSno(sno);
        test.setTno(testNo);
        test.setTscore(score);
        test.setTresult(testResult);
        test.setTtime(date);
        test.insert();
        Student student = new Student();
        student.setSno(sno);
        student = student.selectOne(new EntityWrapper<Student>().eq("sno",sno));
        student.setSclass(classes[score / 100]);
        JSONObject result = new JSONObject();
        result.put("result", true);
        MsgScaleResultRsp msgScaleResultRsp = new MsgScaleResultRsp(getId() + 1, result.toString());
        s2CSession.sendMessage(msgScaleResultRsp.toJson());
    }
}
