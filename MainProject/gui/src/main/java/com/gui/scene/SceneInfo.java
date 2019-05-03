package com.gui.scene;

import com.logic.utilities.validators.StringValidator;

/**
 * <h1>SceneInfo</h1>
 *
 * Immutable class for creating objects holding the view's paths and names
 * Used in the SceneManager class
 *
 * @author Candidate 730
 * @since 15-04-2019
 */

public class SceneInfo {

    private final String sceneName;
    private final String viewPath;


    /**
     * Constructor for creating a new instance of this class with the specified name and path
     *
     * @param sceneName - the name of the scene
     * @param viewPath - the path to this scene's FXML-file
     */
    public SceneInfo(String sceneName, String viewPath) {
        this.sceneName = sceneName;
        this.viewPath = StringValidator.requireNonNullAndNotEmpty(viewPath);
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getViewPath() {
        return viewPath;
    }

    public String toString() {
        return "Scene Name: " + sceneName + " View Path: " + viewPath;
    }
}
