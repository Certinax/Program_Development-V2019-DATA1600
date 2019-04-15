package com.gui;

import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * <h1>MainClass</h1>
 *
 * A main class for launching the application
 *
 * @author Fredrik Pedersen
 * @since 25-03-2019
 */

public class GUIMain extends Application {

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