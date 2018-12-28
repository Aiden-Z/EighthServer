package com.eight.server.Message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eight.server.Database.entity.Consultant;
import com.eight.server.Database.entity.Instructor;
import com.eight.server.Database.entity.Student;
import com.eight.server.WebSocket.S2CSession;
import com.eight.server.WebSocket.SessionState;
import org.json.JSONObject;

public class MsgLogin extends MessageBase {
    @Override
    public void handleMessage(S2CSession session) {
        JSONObject jsonObject = new JSONObject(this.getContent());
        String type = jsonObject.getString("type");
        switch (type) {
            case "student":
            {
                studentLogin(jsonObject, session);
                break;
            }
            case "consultant":
            {
                consultantLogin(jsonObject, session);
                break;
            }
            case "instructor":
            {
                instructorLogin(jsonObject, session);
                break;
            }
        }
    }

    private void studentLogin(JSONObject jsonObject, S2CSession session) {
        String userId = jsonObject.getString("userid");
        String pwd = jsonObject.getString("password");
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>().eq("sno",userId);
        Student student = new Student();
        student.setSno(userId);
        student = student.selectOne(wrapper);
        if (student != null && student.getSpwd().equals(pwd)) {
            System.out.println("student id: " + userId + " logged in");
            if (session.compareSessionState(SessionState.LogingIn)){
                session.loginSession(userId);
            }
            JSONObject result = new JSONObject();
            result.put("userid", userId);
            result.put("result", true);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, result.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }else {
            JSONObject result = new JSONObject();
            result.put("userid", userId);
            result.put("type", "student");
            result.put("result", false);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, result.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }

    }
    private void consultantLogin(JSONObject jsonObject, S2CSession session) {
        String userId = jsonObject.getString("userid");
        String pwd = jsonObject.getString("password");
        Consultant consultant = new Consultant();
        consultant.setCno(userId);
        consultant = consultant.selectOne(new QueryWrapper<Consultant>().eq("cno",userId));
        if (consultant != null && consultant.getCpwd().equals(pwd)) {
            System.out.println("consultant id: " + userId + " logged in");
            if (session.compareSessionState(SessionState.LogingIn)){
                session.loginSession(userId);
            }
            JSONObject result = new JSONObject();
            result.put("userid", userId);
            result.put("result", true);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, result.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }else {
            JSONObject result = new JSONObject();
            result.put("userid", userId);
            result.put("type", "consultant");
            result.put("result", false);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, result.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }
    }
    private void instructorLogin(JSONObject jsonObject, S2CSession session) {
        String userId = jsonObject.getString("userid");
        String pwd = jsonObject.getString("password");
        Instructor instructor = new Instructor();
        instructor.setIno(userId);
        instructor = instructor.selectOne(new QueryWrapper<Instructor>().eq("ino",userId));
        if (instructor != null && instructor.getIpwd().equals(pwd)) {
            System.out.println("instructor id: " + userId + " logged in");
            if (session.compareSessionState(SessionState.LogingIn)){
                session.loginSession(userId);
            }
            JSONObject result = new JSONObject();
            result.put("userid", userId);
            result.put("result", true);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, result.toString());
            session.sendMessage(msgLoginRsp.toJson());
            // todo 检查今天日期，如果是规定的某个日期，并且导员所属的院校有高危学生，发送一条推送消息
        }else {
            JSONObject result = new JSONObject();
            result.put("userid", userId);
            result.put("type", "instructor");
            result.put("result", false);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, result.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }
    }
}
