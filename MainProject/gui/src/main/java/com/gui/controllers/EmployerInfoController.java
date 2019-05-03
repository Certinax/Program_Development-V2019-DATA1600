package com.gui.controllers;

import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//TODO Write JavaDocs!
public class EmployerInfoController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private ErrorBox error;

    @FXML
    Label name, address, zipcode, city, phoneNumber, email, sector, industry;

    @Override
    public void initialize() {
    }

    @Override
    public void refresh() {
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    @Override
    public void exit() {
    }

    /* ------------------------------------------ Menu Methods ----------------------------------------------*/

    @FXML
    private void goToPositionInfo(ActionEvent event) {
        sceneManager.changeScene(SceneName.POSITIONINFO);
    }

    @FXML
    private void goToRegisterEmployer(ActionEvent event) {
        sceneManager.changeScene(SceneName.REGISTEREMPLOYER);
    }

    @FXML
    private void goToRegisterPosition(ActionEvent event) {
        sceneManager.changeScene(SceneName.REGISTERPOSITION);
    }

    @FXML
    private void goToRegisterSubstitute(ActionEvent event) {
        sceneManager.changeScene(SceneName.REGISTERSUBSTITUTE);
    }

    @FXML
    private void goToSubstitutes(ActionEvent event) {
        sceneManager.changeScene(SceneName.SUBSTITUTES);
    }

    @FXML
    private void goToTakenPositions(ActionEvent event) {
        sceneManager.changeScene(SceneName.TAKENPOSITIONS);
    }

    @FXML
    private void goToAvailablePositions(ActionEvent event) {
        sceneManager.changeScene(SceneName.AVAILABLEPOSITIONS);
    }

    @FXML
    private void setFullscreenMode(ActionEvent event) {
        sceneManager.setFullscreen();
    }

    @FXML
    private void setWindowedMode() {
        sceneManager.setWindowed();
    }

    @FXML
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS,2 ,3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
    }
}
