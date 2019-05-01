package com.gui.controllers;

import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.customTextFields.IntField;
import com.logic.utilities.NodeGenerator;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Map;

public class RegisterPositionController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    /* ------------------------------------- fx:id fields --------------------------------------- */
    @FXML
    private ToggleGroup selector;

    @FXML
    private IntField numberOfPositions;

    @FXML
    private ComboBox employer;

    @FXML
    private TextField position;

    @FXML
    private IntField salary;

    @FXML
    private ComboBox industry;

    @FXML
    private IntField duration;

    @FXML
    private DatePicker workStart;

    @FXML
    private DatePicker workEnd;

    @FXML
    private TextArea requiredQualifications;

    @FXML
    private TextArea positionDescription;

    @FXML
    private Button register;

    @FXML
    private AnchorPane parent;

    /* ------------------------------------------ Register Method ------------------------------------------ */

    @FXML
    public void registerPosition(ActionEvent event){
        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(parent);

        for (Map.Entry<Node, Object> item : nodesAndValues.entrySet()) {
            if (item.getKey().getId().equals("numberOfPositions")){
                System.out.println("Test for registering positions: numberOfPositions found");
            }
        }
    }



    @Override
    public void initialize() {
    }

    @Override
    public void refresh() {

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
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS);
        } catch (NoPrimaryStageException e) {
            System.err.println(e.getMessage());
        }
    }
}
