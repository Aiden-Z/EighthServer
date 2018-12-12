package com.eight.server.PsychTest;

import java.util.ArrayList;

public class PsychScale {
    private int identifier;
    private String name;
    private String type;
    private ArrayList<PsychQuestion> psychQuestions;
    private String psychScaleDescription;

    public PsychScale(int identifiers,String name, String psychScaleDescription,String type) {
        this.identifier = identifiers;
        this.name = name;
        this.psychScaleDescription = psychScaleDescription;
        this.type = type;
        psychQuestions = PsychScaleCache.getInstance().getQuestions(identifier);
    }

    public String getPsychScaleDescription() {
        return psychScaleDescription;
    }

    public void setPsychScaleDescription(String psychScaleDescription) {
        this.psychScaleDescription = psychScaleDescription;
    }

    public ArrayList<PsychQuestion> getPsychQuestions() {
        return psychQuestions;
    }

    public void setPsychQuestions(ArrayList<PsychQuestion> psychQuestions) {
        this.psychQuestions = psychQuestions;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
