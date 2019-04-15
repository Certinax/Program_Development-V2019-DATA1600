package com.gui.scene;

import com.gui.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <h1>SceneManager</h1>
 *
 * Enum singleton class used to switch scenes in the application
 *
 *@author Fredrik Pedersen
 *@since 15-04-2019
 */

public enum SceneManager {
    INSTANCE;

    private Stage primaryStage = null;
    private final Map<SceneName, SceneInfo> scenes;
    private boolean initialized;

    private Controller activeController;

    SceneManager() {
        scenes = new HashMap<>();
        createSceneInfos();
    }

    /**
     * Private method used to create SceneInfo objects and link them with the SceneName-enums in a hashmap
     */
    private void createSceneInfos() {
        SceneInfo availablePositions = new SceneInfo("Available Positions","/com/gui/fxml/AvailablePositions.fxml");
        SceneInfo substitutes = new SceneInfo("Substitutes", "/com/gui/fxml/Substitutes.fxml");

        scenes.put(SceneName.AVAILABLEPOSITIONS, availablePositions);
        scenes.put(SceneName.SUBSTITUTES, substitutes);
    }

    /**
     * Method used in the applications main method to set the primary stage
     *
     * @param primaryStage - the programs primary stage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "Primary stage cannot be null, please provide a Stage object");
        this.primaryStage.setTitle("Bad Fantasy");
        primaryStage.setOnCloseRequest(WindowEvent -> {
                if (activeController != null) {
                    activeController.exit();
                }
        });

        initialized = true;
    }

    /**
     * Method used for navigating scenes
     *
     * @param sceneName - the scene you move to
     */
    public void changeScene(SceneName sceneName) {
        Objects.requireNonNull(sceneName);

        if (!scenes.containsKey(sceneName)) {
            throw new InvalidParameterException("Target scene does not extist in SceneManagers scene-list");
        }

        if (activeController != null) {
            activeController.exit();
        }

        SceneInfo sceneInfo = scenes.get(sceneName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneInfo.getViewPath()));
        Pane root;

        try {
            root = loader.load();
            activeController = loader.getController();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(sceneInfo.getSceneName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
