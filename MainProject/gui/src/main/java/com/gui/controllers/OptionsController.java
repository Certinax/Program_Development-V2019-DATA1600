package com.gui.controllers;

import com.gui.alertBoxes.InformationBox;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.logic.filePaths.UserDefinedPaths;
import com.logic.utilities.exceptions.UnsupportedFileFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OptionsController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    private String header = "Wrong File Format";
    private ErrorBox error;


    @Override
    public void initialize() {
    }

    @Override
    public void refresh() {
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    public void setSubstituteCSVPath(ActionEvent event) {

        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            return;
        }

        try {
            UserDefinedPaths.setSubstituteCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
        }
    }

    public void setSubstituteJOBJPath(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            return;
        }

        try {
            UserDefinedPaths.setSubstituteJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
        }
    }

    public void setEmployersCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            return;
        }

        try {
            UserDefinedPaths.setEmployerCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
        }

    }

    public void setEmployersJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            return;
        }

        try {
            UserDefinedPaths.setEmployerJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
        }
    }

    public void setAvailablePositionsCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            return;
        }

        try {
            UserDefinedPaths.setAvailablePositionCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
        }
    }

    public void setAvailablePositionsJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            return;
        }

        try {
            UserDefinedPaths.setAvailablePositionJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
        }
    }

    public void setDefaultPaths(ActionEvent event) {
        UserDefinedPaths.setDefaultPaths();
        InformationBox alert = new InformationBox("All info files have been set to their default values", "All filepaths set to default");
    }

    @Override
    public void exit() {
        FXMLLoader currentLoader = sceneManager.getCurrentLoader();
        Controller activeController = currentLoader.getController();
        activeController.refresh();

        Stage optionsStage = sceneManager.getCurrentPopUpStage();
        optionsStage.close();
        sceneManager.setCurrentPopUpStage(null);
    }
}
