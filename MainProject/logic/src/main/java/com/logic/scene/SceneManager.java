package com.logic.scene;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public enum SceneManager {
    INSTANCE;

    private Stage primaryStage = null;
    private final Map<SceneName, SceneInfo> scenes;
    private boolean initialized;

    SceneManager() {
        scenes = new HashMap<>();


    }

    private void createSceneInfos() {
        SceneInfo AvailablePositions = new SceneInfo("Available Positions","/com/gui/fxml/AvailablePositions.fxml");

        scenes.put(SceneName.AVAILABLEPOSITIONS, AvailablePositions);
    }

}
