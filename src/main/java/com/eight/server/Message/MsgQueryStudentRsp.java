package com.eight.server.Message;

import org.json.JSONObject;

public class MsgQueryStudentRsp extends MessageRspBase {
    public MsgQueryStudentRsp(int id, JSONObject content) {
        super(id, content);
    }
}
