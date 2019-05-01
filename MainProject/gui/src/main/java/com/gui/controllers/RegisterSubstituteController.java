package com.gui.controllers;

import com.data.work.SubReg;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.customTextFields.NameField;
import com.logic.customTextFields.PhoneField;
import com.logic.customTextFields.SalaryField;
import com.logic.customTextFields.ZipCodeField;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;

//TODO Write JavaDocs!
public class RegisterSubstituteController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    Label firstnamelbl, lastnamelbl, birthdaylbl, streetnamelbl, zipcodelbl, citylbl, emaillbl, phonenumberlbl,
            schoolnamelbl, educationlbl, startedlbl, finnishedlbl, workplacelbl, positionlbl, fromlbl, tolbl,
            referenceNamelbl, referenceLastnamelbl, referencePhonelbl, referenceMaillbl, industrylbl, salarylbl;

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
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS);
        } catch (NoPrimaryStageException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void registerSubstitute(ActionEvent event) {
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String address = streetnameField.getText();
        //int zipcode = Integer.parseInt(zipCodeField.getText());
        //LocalDate now = LocalDate.now();
        //LocalDate dob = birthdayField.getValue();
        //int age = Period.between(dob, now).getYears();
        //String city = cityField.getText();
        String industry = "DUMMYDATA";
        //SubReg.subReg();
        //Substitute substitute = new Substitute.Builder(firstname, lastname, address, age, zipcode, city, industry).build();
        /*try {
            WriterThreadStarter.startWriter(substitute, "resources/subreg.csv");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("Du har registrert en vikar med fornavn" + firstname);
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
