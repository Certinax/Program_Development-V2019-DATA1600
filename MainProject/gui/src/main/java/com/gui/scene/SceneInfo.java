package com.gui.scene;

import com.logic.validators.StringValidator;

public class SceneInfo {

    private final String sceneName;
    private final String viewPath;

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
