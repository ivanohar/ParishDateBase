package com.ohar.pdb.model.enums;

public enum FamilyStatus {
    MARR("жанаты / замужам"), NOTMARR("нежанаты / незамужам"),
        DIVORCED("разведзены(ая)"), SEPARATED("разышоўся(лася)"),
            UNREGISTERED("у незарэгістраванных адносінах"), WIDOWER("удавец / удава");
    private String text;

    private FamilyStatus(String text) {
        this.text = text;
    }

    public static FamilyStatus getFamilyStatusByText(String text) {
        for (FamilyStatus familyStatus : FamilyStatus.values()) {
            if (familyStatus.getText().equals(text)) {
                return familyStatus;
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