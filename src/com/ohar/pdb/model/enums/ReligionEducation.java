package com.ohar.pdb.model.enums;

public enum ReligionEducation {
    YES("Рэгулярна"), RERELY("Не рэгулярна"), NO("Не удзельнічае");
    private String text;

    private ReligionEducation(String text) {
        this.text = text;
    }

    public static ReligionEducation getReligionEducationByText(String text) {
        for (ReligionEducation religionEducation : ReligionEducation.values()) {
            if (religionEducation.getText().equals(text)) {
                return religionEducation;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}