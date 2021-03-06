package com.eight.server.Message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eight.server.Database.entity.Student;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

import java.util.List;

public class MsgQueryStudent extends MessageBase {
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        String type = jsonObject.getString("type");
        if (type.equals("consultant")){
            consultantQuery(jsonObject, s2CSession);
        }else if (type.equals("instructor")) {
            instructorQuery(jsonObject, s2CSession);
        }
    }

    private void consultantQuery(JSONObject jsonObject, S2CSession session) {
        Student student = new Student();
        List<Student> students = student.selectList(new QueryWrapper<Student>().eq("sclass","高危"));
        JSONObject result = new JSONObject();
        result.put("dangerousStudents", students);
        MsgQueryStudentRsp msgQueryStudentRsp = new MsgQueryStudentRsp(getId() + 1, result.toString());
        session.sendMessage(msgQueryStudentRsp.toJson());
    }

    private void instructorQuery(JSONObject jsonObject,S2CSession session) {
        String idept = jsonObject.getString("instructorDept");
        Student student = new Student();
        List<Student> students = student.selectList(new QueryWrapper<Student>().eq("sdept","高危"));
        students.removeIf(t->!t.getSdept().equals(idept));
        JSONObject result = new JSONObject();
        result.put("dangerousStudents", students);
        MsgQueryStudentRsp msgQueryStudentRsp = new MsgQueryStudentRsp(getId() + 1, result.toString());
        session.sendMessage(msgQueryStudentRsp.toJson());
    }


}
