package com.gui.scene;

import com.gui.alertBoxes.ErrorBox;
import com.gui.controllers.Controller;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
 *@author Candidate 730
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
    private Stage currentPopUpStage;
    private FXMLLoader currentLoader;
    private ErrorBox error;

    SceneManager() {
        scenes = new HashMap<>();
        createSceneInfos();
    }

    /**
     * Private method used to create SceneInfo objects and link them with the SceneName-enums in a hashmap
     */
    private void createSceneInfos() {
        SceneInfo availablePositions = new SceneInfo("Available Positions","/com/gui/fxml/AvailablePositions.fxml");
        SceneInfo employerInfo = new SceneInfo("Employer Info", "/com/gui/fxml/EmployerInfo.fxml");
        SceneInfo employers = new SceneInfo("Employers", "/com/gui/fxml/Employers.fxml");
        SceneInfo matchSubstitute = new SceneInfo("Match Substitute", "/com/gui/fxml/MatchSubstitute.fxml");
        SceneInfo options = new SceneInfo("Options", "/com/gui/fxml/Options.fxml");
        SceneInfo positionInfo = new SceneInfo("Position Info", "/com/gui/fxml/PositionInfo.fxml");
        SceneInfo registerEmployer = new SceneInfo("Register Employer", "/com/gui/fxml/RegisterEmployer.fxml");
        SceneInfo registerPosition = new SceneInfo("Register Position", "/com/gui/fxml/RegisterPosition.fxml");
        SceneInfo registerSubstitute = new SceneInfo("Register Substitute", "/com/gui/fxml/RegisterSubstitute.fxml");
        SceneInfo substituteInfo = new SceneInfo("Substitute Info", "/com/gui/fxml/SubstituteInfo.fxml");
        SceneInfo substitutes = new SceneInfo("Substitutes", "/com/gui/fxml/Substitutes.fxml");
        SceneInfo takenPositions = new SceneInfo("Taken Positions", "/com/gui/fxml/TakenPositions.fxml");

        scenes.put(SceneName.AVAILABLEPOSITIONS, availablePositions);
        scenes.put(SceneName.EMPLOYERINFO, employerInfo);
        scenes.put(SceneName.EMPLOYERS, employers);
        scenes.put(SceneName.MATCHSUBSTITUTE, matchSubstitute);
        scenes.put(SceneName.OPTIONS, options);
        scenes.put(SceneName.POSITIONINFO, positionInfo);
        scenes.put(SceneName.REGISTEREMPLOYER, registerEmployer);
        scenes.put(SceneName.REGISTERPOSITION, registerPosition);
        scenes.put(SceneName.REGISTERSUBSTITUTE, registerSubstitute);
        scenes.put(SceneName.SUBSTITUTEINFO, substituteInfo);
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

    public void createUndecoratedStageWithScene(Stage popUpStage, SceneName sceneName, double heightDivisor, double widthDivisor) throws NoPrimaryStageException, ExtraStageException {
        if (this.primaryStage == null) {
            throw new NoPrimaryStageException("No primary stage. Do not call this method before a Primary Stage has been defined");
        }

        if (this.currentPopUpStage != null) {
            throw new ExtraStageException("You can only have one pop-up window at the time. \nPlease close the other pop-up window first");
        }

        Objects.requireNonNull(popUpStage, "The new stage can't be null, please provide a Stage object");
        popUpStage.setWidth(this.primaryStage.getWidth()/widthDivisor);
        popUpStage.setHeight(this.primaryStage.getHeight()/heightDivisor);
        popUpStage.setMinHeight(333);
        popUpStage.setMinWidth(400);

        SceneInfo sceneInfo = scenes.get(sceneName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneInfo.getViewPath()));
        Pane root;

        try {
            root = loader.load();
            Scene scene = new Scene(root);
            popUpStage.setScene(scene);
            popUpStage.setTitle(sceneInfo.getSceneName());
        } catch (IOException e) {
            e.printStackTrace();
            error = new ErrorBox("Failed to load FXML-file! \nPlease contact support if the problem persists", "Failed to load FXML file");
        }
        popUpStage.initStyle(StageStyle.UNDECORATED);
        setCurrentPopUpStage(popUpStage);
        popUpStage.show();
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
        setCurrentLoader(loader);
        Pane root;

        try {
            root = loader.load();
            activeController = loader.getController();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(sceneInfo.getSceneName());
        } catch (IOException e) {
            e.printStackTrace();
            error = new ErrorBox("Failed to load FXML-file! \nPlease contact support if the problem persists", "Failed to load FXML file");

        }
    }

    public void setCurrentPopUpStage(Stage popUpStage) {
        this.currentPopUpStage = popUpStage;
    }

    public Stage getCurrentPopUpStage() {
        return currentPopUpStage;
    }

    private void setCurrentLoader(FXMLLoader loader) {
        this.currentLoader = loader;
    }

    public FXMLLoader getCurrentLoader() {
        return currentLoader;
    }

}
