package com.gui.controllers;

import com.data.clients.Employer;
import com.data.clients.Substitute;
import com.data.work.SubReg;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.customTextFields.NameField;
import com.logic.customTextFields.PhoneField;
import com.logic.customTextFields.SalaryField;
import com.logic.customTextFields.ZipCodeField;
import com.logic.utilities.NodeGenerator;
import com.logic.utilities.NodeHandler;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import com.logic.utilities.validators.SubstituteDataValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Map;

//TODO Write JavaDocs!
public class RegisterSubstituteController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    Label firstnamelbl, lastnamelbl, birthdaylbl, streetnamelbl, zipcodelbl, citylbl, emaillbl, phonenumberlbl,
            schoolnamelbl, educationlbl, startedlbl, finnishedlbl, workplacelbl, positionlbl, fromlbl, tolbl,
            referenceNamelbl, referenceLastnamelbl, referencePhonelbl, referenceMaillbl, industrylbl, salarylbl, eduErrorLbl, jobErrorLbl;

    @FXML
    TextField streetnameField, emailField, educationField, workplaceField, positionField, referenceMailField;

    @FXML
    NameField firstnameField, lastnameField, cityField,schoolnameField, referenceNameField, referenceLastnameField;

    @FXML
    PhoneField phoneField, referencePhoneField;

    @FXML
    ZipCodeField zipCodeField;

    @FXML
    SalaryField salaryField;

    @FXML
    DatePicker birthdayField, educationStartField, educationEndField, workStartField, workEndField;

    @FXML
    ComboBox<String> industryList;

    @FXML
    AnchorPane anchPane, infoAnchPane;

    @FXML
    ListView<String> schoolList, workList;

    @FXML
    DatePicker startDate, endDate;

    private ObservableList<String> observableSchool;
    private ObservableList<String> observableJob;


    @Override
    public void initialize() {
        observableSchool = FXCollections.observableArrayList();
        observableJob = FXCollections.observableArrayList();
        schoolList.setItems(observableSchool);
        workList.setItems(observableJob);
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

    /* --------------------------------------- Other Methods --------------------------------------- */

    @FXML
    private void registerSubstitute(ActionEvent event) {

        // This parent holds all the necessary children to retrieve information from
        AnchorPane parent = infoAnchPane;

        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(infoAnchPane);

        for(Map.Entry<Node, Object> item : nodesAndValues.entrySet()) {
            System.out.println(item.getKey().toString());
            if(item.getKey().getId() != null) {
                if (item.getKey().getId().equals("firstnameField")) {
                    System.out.println("Test");
                }
            }
        }

        Substitute substitute = new Substitute.Builder("mathias", "ahrn", "asf",
                12, 122," sad", "asd")
                .education(new ArrayList<>())
                .build();


        System.out.println(SubstituteDataValidator.dataMatching(nodesAndValues));

    }

    @FXML
    public void addSchool(ActionEvent event){
        if(!(schoolnameField.getText().isEmpty() || educationField.getText().isEmpty() || startDate.getEditor().getText().isEmpty() || endDate.getEditor().getText().isEmpty())){
            eduErrorLbl.setVisible(false);
            String theSchool = "";
            theSchool += schoolnameField.getText() + " ";
            theSchool += educationField.getText() + " ";
            theSchool += startDate.getEditor().getText() + " - ";
            theSchool += endDate.getEditor().getText();

            observableSchool.add(theSchool);
        }else {
            eduErrorLbl.setVisible(true);
        }

    }

    @FXML
    public void removeSchool(ActionEvent event){
        if (!observableSchool.isEmpty()){
            observableSchool.remove(observableSchool.size()-1);
        }
    }

    @FXML
    public void addWork(ActionEvent event){
        if(!(workplaceField.getText().isEmpty() || positionField.getText().isEmpty() || workStartField.getEditor().getText().isEmpty() || workEndField.getEditor().getText().isEmpty())){
            jobErrorLbl.setVisible(false);
            String theWork = "";
            theWork += workplaceField.getText() + " ";
            theWork += positionField.getText() + " ";
            theWork += workStartField.getEditor().getText() + " - ";
            theWork += workEndField.getEditor().getText();
            if (!(referenceNameField.getText().isEmpty() || referenceLastnameField.getText().isEmpty() || referencePhoneField.getText().isEmpty() || referenceMailField.getText().isEmpty())) {
                theWork += " " + referenceNameField.getText() + " ";
                theWork += referenceLastnameField.getText() + " ";
                theWork += referencePhoneField.getText() + " ";
                theWork += referenceMailField.getText();
            }

            observableJob.add(theWork);
        }else{
            jobErrorLbl.setVisible(true);
        }
    }

    @FXML
    public void removeWork(ActionEvent event){
        if (!observableJob.isEmpty()){
            observableJob.remove(observableJob.size()-1);
        }
    }




}
