package com.eight.server.PsychTest;

public class PsychQuestion {// 单个选择题
    private String questionContent;

    public PsychQuestion(String questionContent) {
        this.questionContent = questionContent;
    }

    public PsychQuestion() {
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
