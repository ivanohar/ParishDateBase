package com.ohar.pdb.model.enums;

public enum Status {
    ABBOT("Настаяцель"), VICAR("Вікарый"), SECRETARY("Сакратар");
    private String text;

    private Status(String text) {
        this.text = text;
    }

    public static Status getStatusByText(String text) {
        for (Status status : Status.values()) {
            if (status.getText().equals(text)) {
                return status;
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
