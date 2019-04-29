package com.gui.scene;

import com.gui.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
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
    private Screen screen = Screen.getPrimary();
    private Rectangle2D bounds = screen.getVisualBounds();
    private Controller activeController;

    SceneManager() {
        scenes = new HashMap<>();
        createSceneInfos();
    }

    /**
     * Private method used to create SceneInfo objects and link them with the SceneName-enums in a hashmap
     */
    private void createSceneInfos() { //TODO MAKE SURE ALL VIEW ARE REPRESENTED HERE
        SceneInfo availablePositions = new SceneInfo("Available Positions","/com/gui/fxml/AvailablePositions.fxml");
        SceneInfo positionInfo = new SceneInfo("Position Info", "/com/gui/fxml/PositionInfo.fxml");
        SceneInfo registerEmployer = new SceneInfo("Register Employer", "/com/gui/fxml/RegisterEmployer.fxml");
        SceneInfo registerPosition = new SceneInfo("Register Position", "/com/gui/fxml/RegisterPosition.fxml");
        SceneInfo registerSubstitute = new SceneInfo("Register Substitute", "/com/gui/fxml/RegisterSubstitute.fxml");
        SceneInfo substitutes = new SceneInfo("Substitutes", "/com/gui/fxml/Substitutes.fxml");
        SceneInfo takenPositions = new SceneInfo("Taken Positions", "/com/gui/fxml/TakenPositions.fxml");

        scenes.put(SceneName.AVAILABLEPOSITIONS, availablePositions);
        scenes.put(SceneName.POSITIONINFO, positionInfo);
        scenes.put(SceneName.REGISTEREMPLOYER, registerEmployer);
        scenes.put(SceneName.REGISTERPOSITION, registerPosition);
        scenes.put(SceneName.REGISTERSUBSTITUTE, registerSubstitute);
        scenes.put(SceneName.SUBSTITUTES, substitutes);
        scenes.put(SceneName.TAKENPOSITIONS, takenPositions);
    }

    /**
     * Method used in the applications main method to set the primary stage
     *
     * @param primaryStage - the programs primary stage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "Primary stage cannot be null, please provide a Stage object");
        this.primaryStage.setTitle("Bad Fantasy");
        this.primaryStage.setWidth((Screen.getPrimary().getBounds().getWidth())/1.5);
        this.primaryStage.setHeight((Screen.getPrimary().getBounds().getHeight())/1.2);
        this.primaryStage.setMinWidth(530);
        this.primaryStage.setMinHeight(315);

        primaryStage.setOnCloseRequest(WindowEvent -> {
                if (activeController != null) {
                    activeController.exit();
                }
        });

        initialized = true;
    }

    public void setFullscreen() {
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
    }

    public void setWindowed() {
        primaryStage.setFullScreen(false);
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth((bounds.getWidth())/1.5);
        primaryStage.setHeight((bounds.getHeight())/1.2);
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
