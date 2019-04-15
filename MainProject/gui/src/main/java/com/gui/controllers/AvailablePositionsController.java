package com.gui.controllers;

import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AvailablePositionsController implements Controller {

    SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    private void goToSubstitutes(ActionEvent event) {
        sceneManager.changeScene(SceneName.SUBSTITUTES);
    }

    @Override
    public void exit() {

    }
}
