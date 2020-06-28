package com.ohar.pdb.helper;

public enum WindowConfig {
    registration("/com/ohar/pdb/fxmls/registration.fxml", "", 500, 310),
    authorisation("/com/ohar/pdb/fxmls/authorisation.fxml", "", 500, 310),
    findWin("/com/ohar/pdb/fxmls/findWin.fxml", "", 900, 600),
    mainWin("/com/ohar/pdb/fxmls/mainWin.fxml", "", 900, 600),
    userWin("/com/ohar/pdb/fxmls/userWin.fxml", "", 900, 600),
    workWin("/com/ohar/pdb/fxmls/workWin.fxml", "", 900, 600),
    familyWin( "/com/ohar/pdb/fxmls/familyWin.fxml","",420,595);

    private String xmlUi;
    private String title;
    private int width;
    private int height;

    WindowConfig(String xmlUi, String title, int width, int height) {
        this.xmlUi = xmlUi;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public String getXmlUi() {
        return xmlUi;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
