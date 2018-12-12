package com.eight.server.PsychTest;

import java.util.ArrayList;
import java.util.Vector;

public class PsychScaleCache {// todo 全局单例，饿加载，在构造时将所有测试题从数据库中读出
    private static PsychScaleCache instance = new PsychScaleCache();
    private Vector<ArrayList<PsychQuestion>> psychScaleCache;
    private PsychScaleCache() {
        // todo 将所有题目从数据库中取出放入cache
        psychScaleCache = new Vector<>(); // 因为只有读，写只有这一次，所以不用考虑线程安全问题
    }
    public static PsychScaleCache getInstance(){
        return instance;
    }
    public ArrayList<PsychQuestion> getQuestions(int scaleIdentifier) {
        ArrayList<PsychQuestion> questions = null;
        try {
            questions = psychScaleCache.get(scaleIdentifier);
        } catch (ArrayIndexOutOfBoundsException e) {
            return questions;
        }
        return new ArrayList<>(questions);
    }
}
