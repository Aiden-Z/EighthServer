package com.eight.server.Message;

public class MsgPushExamineRsp extends MessageRspBase { // 用于给辅导员推送月排查
    public MsgPushExamineRsp(Integer id, String content) {
        super(id, content);
    }
}
