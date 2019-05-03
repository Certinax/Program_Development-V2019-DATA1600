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

/**
 * <h1>Options Controller</h1>
 *
 * Controller for the options view.
 * This view is just used to set the programs filepaths for the session.
 */

public class OptionsController implements Controller {

    private SceneManager sceneManager = SceneManager.INSTANCE;
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

    private File useFileChooser(String fileType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Find data files");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(fileType, "*."+fileType));

        return fileChooser.showOpenDialog(null);
    }

    public void setSubstituteCSVPath(ActionEvent event) {
        selectedFile = useFileChooser("csv");

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
        selectedFile = useFileChooser("jobj");

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
        selectedFile = useFileChooser("csv");

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
        selectedFile = useFileChooser("jobj");

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
        selectedFile = useFileChooser("csv");

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
        selectedFile = useFileChooser("jobj");

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
