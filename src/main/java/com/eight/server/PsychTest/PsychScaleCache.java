package com.eight.server.PsychTest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class PsychScaleCache {
    private static PsychScaleCache instance = new PsychScaleCache();
    private Map<String, PsychScale> psychScaleCache;
    private JSONObject jsonObject;
    public boolean addPsychScale(PsychScale psychScale) {
        if (psychScaleCache.get(psychScale.getIdentifier()) == null) {
            psychScaleCache.put(psychScale.getIdentifier(), psychScale);
            JSONObject scale = new JSONObject(psychScale);
            jsonObject.put(psychScale.getIdentifier(), scale);
            return true;
        }else {
            return false;
        }
    }
    public void saveFile() {
        File file = new File(this.getClass().getResource("/static/PsychQuestionConfig").getFile());
        if (file.exists()) {
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file.getPath());
                StringBuffer stringBuffer = new StringBuffer(jsonObject.toString());
                outputStream.write(stringBuffer.toString().getBytes("utf-8"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private PsychScaleCache() {
        // todo 将所有题目从数据库中取出放入cache
        InputStream inputStream = this.getClass().getResourceAsStream("/static/PsychQuestionConfig");
        if (inputStream != null) {
            Scanner scanner = new Scanner(inputStream);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.next());
            }
            jsonObject = new JSONObject(stringBuilder.toString());
        }
        psychScaleCache = new HashMap<>(); // 因为只有读，写只有这一次，所以不用考虑线程安全问题
        if (jsonObject == null) {
            return;
        }
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject object = jsonObject.getJSONObject(key);
            ArrayList<PsychQuestion> questionArrayList = new ArrayList<>();
            JSONArray objects = object.getJSONArray("questions");
            for (int i = 0; i < objects.length(); i++) {
                questionArrayList.add(new PsychQuestion(objects.getJSONObject(i).getString("questionContent")));
            }
            PsychScale psychScale = new PsychScale(key, object.getString("name"), object.getString("desc"), object.getString("type"), questionArrayList);
            psychScaleCache.put(key, psychScale);
        }
    }
    public static PsychScaleCache getInstance(){
        return instance;
    }
    public PsychScale getPsychScale(String scaleIdentifier) {
        PsychScale psychScale = psychScaleCache.get(scaleIdentifier);
        return psychScale;
    }
}
