package com.gui.controllers;

import com.data.Industry;
import com.data.factory.SubstituteFactory;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.customTextFields.NameField;
import com.logic.customTextFields.PhoneField;
import com.logic.customTextFields.SalaryField;
import com.logic.customTextFields.ZipCodeField;
import com.logic.utilities.NodeGenerator;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import com.logic.utilities.validators.RequiredDataContainer;
import com.logic.utilities.validators.ObjectDataValidator;
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
public class RegisterSubstituteController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    Label firstnamelbl, lastnamelbl, birthdaylbl, streetnamelbl, zipcodelbl, citylbl, emaillbl, phonenumberlbl,
            schoolnamelbl, educationlbl, startedlbl, finnishedlbl, workplacelbl, positionlbl, fromlbl, tolbl,
            referenceNamelbl, referenceLastnamelbl, referencePhonelbl, referenceMaillbl, industrylbl, salarylbl;

    @FXML
    TextField address, emailField, educationField, workplaceField, positionField, referenceMailField;

    @FXML
    NameField firstname, lastname, city, schoolnameField, referenceNameField, referenceLastnameField;

    @FXML
    PhoneField phoneField, referencePhoneField;

    @FXML
    ZipCodeField zipcode;

    @FXML
    SalaryField salaryRequirement;

    @FXML
    DatePicker age, educationStartField, educationEndField, workStartField, workEndField;

    @FXML
    ComboBox<String> industry;

    @FXML
    AnchorPane anchPane, infoAnchorPane;

    @FXML
    ScrollPane scrollPane;


    @Override
    public void initialize() {
        ObservableList<String> oIndustryList = FXCollections.observableArrayList(Industry.industryList());
        industry.setItems(oIndustryList.sorted());
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
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS);
        } catch (NoPrimaryStageException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void registerSubstitute(ActionEvent event) {
        String error = "";

        // This parent holds all the necessary children to retrieve information from
        AnchorPane parent = infoAnchorPane;

        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(infoAnchorPane);


        if(ObjectDataValidator.requiredDataMatching(nodesAndValues, RequiredDataContainer.SUBSTITUTE.requiredData())) {
            // Opprett objekt
            try {
                SubstituteFactory substitute = new SubstituteFactory(nodesAndValues);
            } catch (IllegalArgumentException | InterruptedException e) {
                error += e.getMessage();
                e.printStackTrace();
                scrollPane.setVvalue(0);
                // TODO Sett in error label/popup med error-variabel
            }
        } else {
                error += "You need to fill the required fields:\n " +
                        "- Firstname\n" +
                        "- Lastname\n" +
                        "- Address\n" +
                        "- Date of birth\n" +
                        "- Zipcode\n" +
                        "- City\n" +
                        "- Industry";
                scrollPane.setVvalue(0);
                // TODO Sett en error label/popup med error-variabel
        }

    }

    @FXML
    private void setFullscreenMode(ActionEvent event) {
        sceneManager.setFullscreen();
    }

    @FXML
    private void setWindowedMode() {
        sceneManager.setWindowed();
    }
}
