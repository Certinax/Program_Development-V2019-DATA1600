package com.gui.controllers;

import com.gui.alertBoxes.AlertBox;
import com.gui.alertBoxes.ErrorBox;
import com.logic.filePaths.UserDefinedPaths;
import com.logic.utilities.exceptions.UnsupportedFileFormatException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import java.io.File;

public class OptionsController implements Controller {

    private FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    private String header = "Wrong File Format";
    private ErrorBox error;
    private AlertBox alert;

    public void setSubstituteCSVPath(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setSubstituteCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setSubstituteJOBJPath(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setSubstituteJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setEmployersCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setEmployerCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }

    }

    public void setEmployersJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setEmployerJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setAvailablePositionsCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setAvailablePositionCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setAvailablePositionsJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setAvailablePositionJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setActivePositionsCSV(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setActivePositionCSVPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setActivePositionsJOBJ(ActionEvent event) {
        selectedFile = fileChooser.showOpenDialog(null);
        try {
            UserDefinedPaths.setActivePositionJOBJPath(selectedFile.toString());
        } catch (UnsupportedFileFormatException e) {
            error = new ErrorBox(e.getMessage(), header);
            error.show();
        }
    }

    public void setDefaultPaths(ActionEvent event) {
        UserDefinedPaths.setDefaultPaths();
        alert = new AlertBox("All info files have been set to their default values", "All filepaths set to default");
        alert.show();
    }

    @Override
    public void exit() {

    }
}
