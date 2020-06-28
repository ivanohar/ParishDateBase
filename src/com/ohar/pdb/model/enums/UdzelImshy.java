package com.ohar.pdb.model.enums;

public enum UdzelImshy {
    OFTEN("Часта"), RARELY("Рэдка"), NEVER("Ніколі");
    private String text;

    private UdzelImshy(String text) {
        this.text = text;
    }

    public static UdzelImshy getUdzelImshyByText(String text) {
        for (UdzelImshy udzelImshy : UdzelImshy.values()) {
            if (udzelImshy.getText().equals(text)) {
                return udzelImshy;
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
