package com.gui.controllers;

import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SubstitutesController implements Controller {
    SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    private void goToAvailablePositions(ActionEvent event) {
        sceneManager.changeScene(SceneName.AVAILABLEPOSITIONS);
    }

    public void exit() {
    }
}