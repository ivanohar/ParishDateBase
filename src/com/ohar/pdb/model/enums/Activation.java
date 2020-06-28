package com.ohar.pdb.model.enums;

public enum Activation {
    YES("Так"), NO("Не"), BLOCKED("Заблакаваны");
    private String text;

    private Activation(String text) {
        this.text = text;
    }

    public static Activation getActivationByText(String text) {
        for (Activation activation : Activation.values()) {
            if (activation.getText().equals(text)) {
                return activation;
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


