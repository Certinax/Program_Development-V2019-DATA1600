package com.gui.controllers;

import com.gui.alertBoxes.ErrorBox;
import com.data.clients.Substitute;
import com.gui.scene.SceneManager;
import com.logic.utilities.DataPasser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

//TODO Write JavaDocs!
public class SubstituteInfoController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;


    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        setData((Substitute) DataPasser.getData());
    }

    @Override
    public void refresh() {
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    @Override
    public void exit() {
        sceneManager.getCurrentPopUpStage().close();
        sceneManager.setCurrentPopUpStage(null);
    }


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
}
