package com.gui.controllers;

import com.data.Industry;
import com.data.factory.SubstituteFactory;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.customTextFields.NameField;
import com.logic.customTextFields.PhoneField;
import com.logic.customTextFields.SalaryField;
import com.logic.customTextFields.ZipCodeField;
import com.logic.utilities.NodeGenerator;
import com.logic.utilities.exceptions.ExtraStageException;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//TODO Write JavaDocs!
public class RegisterSubstituteController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    private Label firstnamelbl, lastnamelbl, birthdaylbl, streetnamelbl, zipcodelbl, citylbl, emaillbl, phonenumberlbl,
            schoolnamelbl, educationlbl, startedlbl, finnishedlbl, workplacelbl, positionlbl, fromlbl, tolbl,
            referenceNamelbl, referenceLastnamelbl, referencePhonelbl, referenceMaillbl, industrylbl, salarylbl,
            eduErrorLbl, jobErrorLbl, refErrorLbl, errorMsg;

    @FXML
    private TextField address, emailField, educationField, workplaceField, positionField, referenceMailField;

    @FXML
    private NameField firstname, lastname, city, schoolnameField, referenceNameField, referenceLastnameField;

    @FXML
    private PhoneField phoneField, referencePhoneField;

    @FXML
    private ZipCodeField zipcode;

    @FXML
    private SalaryField salaryRequirement;

    @FXML
    private DatePicker age, educationStartField, educationEndField, workStartField, workEndField;

    @FXML
    private ComboBox<String> industry;

    @FXML
    private AnchorPane anchPane, infoAnchorPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ListView<String> schoolList, workList, referenceList;

    @FXML
    private DatePicker startDate, endDate;

    ErrorBox error;
    private ObservableList<String> observableSchool;
    private ObservableList<String> observableJob;
    private ObservableList<String> observableRef;


    @Override
    public void initialize() {
        observableSchool = FXCollections.observableArrayList();
        observableJob = FXCollections.observableArrayList();
        observableRef = FXCollections.observableArrayList();
        schoolList.setItems(observableSchool);
        workList.setItems(observableJob);
        referenceList.setItems(observableRef);
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

    /* --------------------------------------- Other Methods --------------------------------------- */

    @FXML
    private void registerSubstitute(ActionEvent event) {
        String error = "";

        // This parent holds all the necessary children to retrieve information from
        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(infoAnchorPane);


        if(ObjectDataValidator.requiredDataMatching(nodesAndValues, RequiredDataContainer.SUBSTITUTE.requiredData())) {
            // Opprett objekt
            try {
                SubstituteFactory substitute = new SubstituteFactory(nodesAndValues);
            } catch (IllegalArgumentException | InterruptedException e) {
                error += e.getMessage();
                e.printStackTrace();
                scrollPane.setVvalue(0);
                errorMsg.setText(error);
                errorMsg.setVisible(true);
                // TODO Sett in error label/popup med error-variabel
            }
        } else {
                error += "You need to fill the required fields:\n " +
                        "Firstname" +
                        ", Lastname" +
                        ", Address" +
                        ", Phonenumber" +
                        ", Email" +
                        ", Date of birth" +
                        ", Zipcode" +
                        ", City" +
                        ", Industry";
                scrollPane.setVvalue(0);
                errorMsg.setText(error);
                errorMsg.setVisible(true);
                // TODO Sett en error label/popup med error-variabel
        }

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

    @FXML
    public void addReference(ActionEvent event){
        if(!(referenceNameField.getText().isEmpty() || referenceLastnameField.getText().isEmpty() || referencePhoneField.getText().isEmpty() || referenceMailField.getText().isEmpty())){
            refErrorLbl.setVisible(false);
            String theRef = "";

            theRef += referenceNameField.getText() + " ";
            theRef += referenceLastnameField.getText() + " ";
            theRef += referencePhoneField.getText() + " ";
            theRef += referenceMailField.getText();


            observableRef.add(theRef);
        }else{
            refErrorLbl.setVisible(true);
        }
    }

    @FXML
    public void removeReference(ActionEvent event){
        if (!observableRef.isEmpty()){
            observableRef.remove(observableRef.size()-1);
        }
    }




}
