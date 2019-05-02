package com.gui.controllers;

import com.gui.alertBoxes.ErrorBox;
import com.data.clients.Substitute;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

//TODO Write JavaDocs!
public class SubstituteInfoController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;

    /* ------------------------------------------ variables ----------------------------------------------- */

    @FXML
    Label firstnameLbl, lastnameLbl, ageLbl, streetnameLbl, zipcodeLbl, cityLbl, datefromLbl, datetoLbl;
    @FXML
    ListView<String> education, work, reference;
    @FXML
    Label emailLbl, phoneLbl, industryLbl, salaryLbl;

    private ErrorBox error;
    private ObservableList<String> eduList = FXCollections.observableArrayList();
    private ObservableList<String> jobList = FXCollections.observableArrayList();
    private ObservableList<String> refList = FXCollections.observableArrayList();

    private void setData(Substitute substitute) {
        firstnameLbl.setText(substitute.getFirstname());
        lastnameLbl.setText(substitute.getLastname());
        ageLbl.setText(String.valueOf(substitute.getAge()));
        streetnameLbl.setText(substitute.getAddress());
        zipcodeLbl.setText(String.valueOf(substitute.getZipcode()));
        cityLbl.setText(substitute.getCity());

        emailLbl.setText(substitute.getEmail());
        phoneLbl.setText(String.valueOf(substitute.getPhoneNumber()));
        industryLbl.setText(substitute.getIndustry());
        salaryLbl.setText(String.valueOf(substitute.getSalaryRequirement()));

        for (String edu : substitute.getEducation()) {
            education.setItems(eduList);
        }
        for (String job : substitute.getWorkExperience()) {
            work.setItems(jobList);
        }
        for (String ref : substitute.getWorkReference()) {
            reference.setItems(refList);
        }
    }

    @Override
    public void initialize() {
        // TODO get substitute and call setData(substitute)
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
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS,2,3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
    }
}
