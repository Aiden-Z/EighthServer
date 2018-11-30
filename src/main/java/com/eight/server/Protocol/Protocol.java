package com.eight.server.Protocol;

import org.json.JSONObject;
import java.io.InputStream;
import java.util.*;

public class Protocol {
    private JSONObject protocolMap;
    private Protocol() {
        InputStream inputStream = this.getClass().getResourceAsStream("/static/ProtocolConfig");
        if (inputStream != null) {
            Scanner scanner = new Scanner(inputStream);
            String str = scanner.next();
            this.protocolMap= new JSONObject(str);
        }
    }

    private static class ProtocolHandler{
        private static Protocol INSTANCE = new Protocol();
    }

    public static Protocol getInstance() {
        return ProtocolHandler.INSTANCE;
    }

    public String get(String key) {
        return protocolMap.getString(key);
    }
}
