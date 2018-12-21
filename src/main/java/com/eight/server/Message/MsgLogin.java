package com.eight.server.Message;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
        String userid = jsonObject.getString("userid");
        String pwd = jsonObject.getString("password");
        Student student = new Student();
        student.setSno(userid);
        student = student.selectOne(new EntityWrapper<Student>().eq("sname",userid));
        if (student != null && student.getSpwd().equals(pwd)) {
            System.out.println("user id: " + userid + " logged in");
            if (session.compareSessionState(SessionState.LogingIn)){
                session.loginSession(userid);
            }
            JSONObject temp = new JSONObject();
            temp.put("userid", userid);
            temp.put("result", true);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }else {
            JSONObject temp = new JSONObject();
            temp.put("userid", userid);
            temp.put("result", false);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }

    }
    private void consultantLogin(JSONObject jsonObject, S2CSession session) {
        String userid = jsonObject.getString("userid");
        String pwd = jsonObject.getString("password");
        Consultant consultant = new Consultant();
        consultant.setCno(userid);
        consultant = consultant.selectOne(new EntityWrapper<Student>().eq("sname",userid));
        if (consultant != null && consultant.getCpwd().equals(pwd)) {
            System.out.println("user id: " + userid + " logged in");
            if (session.compareSessionState(SessionState.LogingIn)){
                session.loginSession(userid);
            }
            JSONObject temp = new JSONObject();
            temp.put("userid", userid);
            temp.put("result", true);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }else {
            JSONObject temp = new JSONObject();
            temp.put("userid", userid);
            temp.put("result", false);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }
    }
    private void instructorLogin(JSONObject jsonObject, S2CSession session) {
        String userid = jsonObject.getString("userid");
        String pwd = jsonObject.getString("password");
        Instructor instructor = new Instructor();
        instructor.setIno(userid);
        instructor = instructor.selectOne(new EntityWrapper<Student>().eq("sname",userid));
        if (instructor != null && instructor.getIpwd().equals(pwd)) {
            System.out.println("user id: " + userid + " logged in");
            if (session.compareSessionState(SessionState.LogingIn)){
                session.loginSession(userid);
            }
            JSONObject temp = new JSONObject();
            temp.put("userid", userid);
            temp.put("result", true);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }else {
            JSONObject temp = new JSONObject();
            temp.put("userid", userid);
            temp.put("result", false);
            MsgLoginRsp msgLoginRsp = new MsgLoginRsp(this.getId() + 1, temp.toString());
            session.sendMessage(msgLoginRsp.toJson());
        }
    }
}
