package com.ohar.pdb.model.enums;

public enum Gender {
    MALE("Мужчынскі"), FEMALE("Жаночы");
    private String text;

    private Gender(String text) {
        this.text = text;
    }

    public static Gender getGenderByText(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.getText().equals(text)) {
                return gender;
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
