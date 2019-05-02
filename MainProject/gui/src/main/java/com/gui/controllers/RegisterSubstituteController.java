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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//TODO Write JavaDocs!
public class RegisterSubstituteController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    Label firstnamelbl, lastnamelbl, birthdaylbl, streetnamelbl, zipcodelbl, citylbl, emaillbl, phonenumberlbl,
            schoolnamelbl, educationlbl, startedlbl, finnishedlbl, workplacelbl, positionlbl, fromlbl, tolbl,
            referenceNamelbl, referenceLastnamelbl, referencePhonelbl, referenceMaillbl, industrylbl, salarylbl,
            eduErrorLbl, jobErrorLbl, errorMsg;

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
    @SuppressWarnings("unchecked")
    private void registerSubstitute(ActionEvent event) {
        String error = "";

        // This parent holds all the necessary children to retrieve information from
        AnchorPane parent = infoAnchorPane;

        Map<Node, Object> nodesAndValues = NodeGenerator.generateNodesAndValues(infoAnchorPane);


        for (Map.Entry<Node, Object> entry : nodesAndValues.entrySet()) {
            System.out.println("Node ID: " + entry.getKey().getId() + " Value : " + entry.getValue());
            if(entry.getKey().getId().toString().equals("schoolList")) {
                System.out.println("HER HAR JEG LISTA");
                System.out.println(entry.getKey().getTypeSelector());
                //ObservableList<String> szz = ((ListView) entry.getValue()).getItems();
                System.out.println(entry.getValue().getClass());

                //System.out.println("Lengden på obsList: " + szz.size());
                Collection<String> sda = (ObservableList<String>)entry.getValue();
                ArrayList<String> list = new ArrayList<>();
                for (String s : sda) {
                    list.add(s);
                }

                System.out.println(list.toString());

                System.out.println("LENGDEN TIL COLLECTION: " + sda.size());
            }
        }

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
                        "- Firstname" +
                        ", Lastname" +
                        ", Address" +
                        ", Date of birth" +
                        ", Zipcode" +
                        ", City" +
                        ", Industry -";
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




}
