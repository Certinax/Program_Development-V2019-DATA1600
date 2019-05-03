package com.gui.controllers;

import com.data.Industry;
import com.data.factory.EmployerFactory;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.customTextFields.NameField;
import com.logic.customTextFields.PhoneField;
import com.logic.customTextFields.ZipCodeField;
import com.logic.utilities.NodeGenerator;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import com.logic.utilities.validators.ObjectDataValidator;
import com.logic.utilities.validators.RequiredDataContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Map;

//TODO Write JavaDocs!
public class RegisterEmployerController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    Label errorMsg;
    @FXML
    ToggleGroup sector;
    @FXML
    RadioButton publicSector, privateSector;
    @FXML
    AnchorPane parent;
    @FXML
    NameField name;
    @FXML
    ComboBox<String> industry;
    @FXML
    ZipCodeField zipcode;
    @FXML
    TextField address, city, email;
    @FXML
    PhoneField phoneNumber;
    @FXML
    ScrollPane scrollPane;

    ErrorBox error;

    @Override
    public void initialize() {
        ObservableList<String> oIndustryList = FXCollections.observableArrayList(Industry.industryList());
        industry.setItems(oIndustryList.sorted());
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
    private void goToEmployers(ActionEvent event) {
        sceneManager.changeScene(SceneName.EMPLOYERS);
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
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS, 2, 3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
    }

    @FXML
    private void registerEmployer(ActionEvent event) {
        String error = "";

        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(parent);

        if(ObjectDataValidator.requiredDataMatching(nodesAndValues, RequiredDataContainer.EMPLOYER.requiredData())) {
            // Opprett object
            try {
                EmployerFactory employer = new EmployerFactory(nodesAndValues);
            } catch (IllegalArgumentException | InterruptedException e) {
                error += e.getMessage();
                e.printStackTrace();
                scrollPane.setVvalue(0);
                errorMsg.setText(error);
                errorMsg.setVisible(true);
            }
        } else {
            error += "You need to fill the required fields:\n" +
                    "Name" +
                    ", Address" +
                    ", Zipcode" +
                    ", City" +
                    ", Phonenumber" +
                    ", Email" +
                    ", Sector" +
                    ", Industry";
            scrollPane.setVvalue(0);
            errorMsg.setText(error);
            errorMsg.setVisible(true);
        }
    }
}
