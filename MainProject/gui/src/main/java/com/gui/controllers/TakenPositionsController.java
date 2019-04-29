package com.gui.controllers;

import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//TODO Write JavaDocs!
public class TakenPositionsController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    private void goToAvailablePositions(ActionEvent event) {
        sceneManager.changeScene(SceneName.AVAILABLEPOSITIONS);
    }

    @Override
    public void exit() {

    }
}
