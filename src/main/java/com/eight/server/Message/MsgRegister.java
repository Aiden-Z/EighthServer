package com.eight.server.Message;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eight.server.Database.entity.Consultant;
import com.eight.server.Database.entity.Instructor;
import com.eight.server.Database.entity.Student;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

public class MsgRegister extends MessageBase{
    @Override
    public void handleMessage(S2CSession session) {
        JSONObject jsonObject = new JSONObject(this.getContent());
        String type = jsonObject.getString("type");
        switch (type){
            case "instructor":
            {
                registerInstructor(jsonObject, session);
                break;
            }
            case "consultant":
            {
                registerConsultant(jsonObject, session);
                break;
            }
            case "student":
            {
                registerStudent(jsonObject, session);
                break;
            }
        }
    }

    private void registerStudent(JSONObject jsonObject, S2CSession session) {
        String userid = jsonObject.getString("userid");
        String phone = jsonObject.getString("phone");
        String pwd = jsonObject.getString("password");
        Student student = new Student();
        student.setSno(userid);
        student = student.selectOne(new EntityWrapper<Student>().eq("sno",userid));
        JSONObject temp = new JSONObject();
        if (student == null) {
            temp.put("userid", userid);
            temp.put("result", true);
            student = new Student();
            student.setSno(userid);
            student.setSphone(phone);
            student.setSpwd(pwd);
            student.setSname("empty");
            student.setSdept("empty");
            student.setScon("empty");
            student.setSconph("empty");
            student.insert();
        } else {
            temp.put("userid", userid);
            temp.put("result", false);
        }
        MsgRegisterRsp msgRegisterRsp = new MsgRegisterRsp(this.getId() + 1, temp.toString());
        session.sendMessage(msgRegisterRsp.toJson());
    }
    private void registerConsultant(JSONObject jsonObject, S2CSession session) {
        String userid = jsonObject.getString("userid");
        String phone = jsonObject.getString("phone");
        String pwd = jsonObject.getString("password");
        Consultant consultant = new Consultant();
        consultant.setCno(userid);
        consultant = consultant.selectOne(new EntityWrapper<Consultant>().eq("Cname",userid));
        JSONObject temp = new JSONObject();
        if (consultant == null) {
            temp.put("userid", userid);
            temp.put("result", true);
            consultant = new Consultant();
            consultant.setCno(userid);
            consultant.setCname("empty");
            consultant.setCphone(phone);
            consultant.setCpwd(pwd);
            consultant.insert();
        } else {
            temp.put("userid", userid);
            temp.put("result", false);
        }
        MsgRegisterRsp msgRegisterRsp = new MsgRegisterRsp(this.getId() + 1, temp.toString());
        session.sendMessage(msgRegisterRsp.toJson());
    }

    private void registerInstructor(JSONObject jsonObject, S2CSession session) {
        String userid = jsonObject.getString("userid");
        String phone = jsonObject.getString("phone");
        String pwd = jsonObject.getString("password");
        Instructor instructor = new Instructor();
        instructor.setIno(userid);
        instructor = instructor.selectOne(new EntityWrapper<Consultant>().eq("Cname",userid));
        JSONObject temp = new JSONObject();
        if (instructor == null) {
            temp.put("userid", userid);
            temp.put("result", true);
            instructor = new Instructor();
            instructor.setIno(userid);
            instructor.setIname("empty");
            instructor.setIphone(phone);
            instructor.setIpwd(pwd);
            instructor.setIdept("empty");
            instructor.insert();
        } else {
            temp.put("userid", userid);
            temp.put("result", false);
        }
        MsgRegisterRsp msgRegisterRsp = new MsgRegisterRsp(this.getId() + 1, temp.toString());
        session.sendMessage(msgRegisterRsp.toJson());
    }
}
