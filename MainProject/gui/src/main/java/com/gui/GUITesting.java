package com.gui;

import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUITesting extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneManager sceneManager = SceneManager.INSTANCE;
        sceneManager.setPrimaryStage(stage);
        sceneManager.changeScene(SceneName.AVAILABLEPOSITIONS);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}