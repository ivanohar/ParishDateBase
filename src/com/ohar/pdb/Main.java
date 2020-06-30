package com.ohar.pdb;

import com.ohar.pdb.helper.StageHelper;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageHelper helper = StageHelper.getInstance();
        helper.initializeStage(primaryStage);
        helper.forwardAuthorisation();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

