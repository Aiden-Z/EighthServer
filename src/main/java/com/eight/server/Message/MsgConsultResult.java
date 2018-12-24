package com.eight.server.Message;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.eight.server.Database.entity.Consult;
import com.eight.server.WebSocket.S2CSession;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * 用于客户端上报咨询填写结果
 * todo 数据库consult表中还缺少学生咨询的问题内容，目前咨询内容和咨询结果是共用一个字段
 * 咨询记录的编号由时间+来访者编号拼接生成，但是数据库内编号仅提供15位，因此这种方法还是有重复的风险，只要将编号长度更改为getTime返回值的长度与15之和即能保证在正常情况下不出错
 */
public class MsgConsultResult extends MessageBase {
    @Override
    public void handleMessage(S2CSession s2CSession) {
        JSONObject jsonObject = new JSONObject(getContent());
        String type = jsonObject.getString("type");
        switch (type){
            case "student":
            {
                handleStudent(jsonObject, s2CSession);
                break;
            }
            case "consultant":
            {
                handleConsultant(jsonObject, s2CSession);
                break;
            }
        }
    }

    private void handleStudent(JSONObject jsonObject, S2CSession s2CSession) {

    }

    private void handleConsultant(JSONObject jsonObject, S2CSession s2CSession) {
        String recordNo = jsonObject.getString("recordNo");
        String sno = jsonObject.getString("studentNo");
        String cno = jsonObject.getString("consultantNo");
        String consultResult = jsonObject.getString("consultContent");

        Consult consult = new Consult();
        List<Consult> consults = consult.selectList(new EntityWrapper<Consult>().eq("recordno", recordNo));
        if (consults.size() == 1) { // 一般情况下同个编号只有一个咨询记录，但是不排除非正常情况下出现问题
            consult = consults.get(0);
            consult.setConsultresult(consultResult);
            consult.update(new EntityWrapper<Consult>().eq("recordno", recordNo));
            JSONObject result = new JSONObject();
            result.put("studentNo", sno);
            result.put("consultantNo", cno);
            result.put("result", true);

            MsgConsultResultRsp msgConsultResultRsp = new MsgConsultResultRsp(getId() + 1, result.toString());
            s2CSession.sendMessage(msgConsultResultRsp.toJson());
        } else {
            JSONObject result = new JSONObject();
            result.put("studentNo", sno);
            result.put("consultantNo", cno);
            result.put("result", false);

            MsgConsultResultRsp msgConsultResultRsp = new MsgConsultResultRsp(getId() + 1, result.toString());
            s2CSession.sendMessage(msgConsultResultRsp.toJson());
        }

    }
}
