package com.gui.controllers;

import com.gui.alertBoxes.AlertBox;
import com.gui.alertBoxes.ErrorBox;
import com.logic.filePaths.UserDefinedPaths;
import com.logic.utilities.exceptions.UnsupportedFileFormatException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OptionsController implements Controller {

    FileChooser fileChooser = new FileChooser();
    Stage chooserStage = new Stage();
    File selectedFile;
    String header = "Wrong File Format";

    public void setSubstituteCSVPath(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setSubstituteCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setSubstituteJOBJPath(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setSubstituteJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setEmployersCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setEmployerCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }

    }

    public void setEmployersJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setEmployerJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setAvailablePositionsCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setAvailablePositionCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setAvailablePositionsJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setAvailablePositionJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setActivePositionsCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setActivePositionCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setActivePositionsJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(chooserStage);
        try {
            UserDefinedPaths.setActivePositionJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            ErrorBox alert = new ErrorBox(e.getMessage(), header);
            alert.show();
        }
    }

    public void setDefaultPaths(ActionEvent event) {
        UserDefinedPaths.setDefaultPaths();
        AlertBox alert = new AlertBox("All info files have been set to their default values", "All filepaths set to default");
    }
    public void exit() {

    }
}
