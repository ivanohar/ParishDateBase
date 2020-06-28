package com.ohar.pdb.model.enums;

public enum Spovedz {
    OFTEN("Часта"), RARELY("Рэдка"), NEVER("Ніколі");
    private String text;

    private Spovedz(String text) {
        this.text = text;
    }

    public static Spovedz getSpovedzByText(String text) {
        for (Spovedz spovedz : Spovedz.values()) {
            if (spovedz.getText().equals(text)) {
                return spovedz;
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
