package com.gui.controllers;

import com.data.clients.Substitute;
import com.gui.alertBoxes.AlertBox;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.concurrent.ExecutionException;

public class MatchSubstituteController implements Controller {


    @FXML
    private TableView<Substitute> tableView;

    @FXML
    private ObservableList<Substitute> data;

    @FXML
    private TableColumn<Substitute, String> fnameColumn, lnameColumn, addressColumn, cityColumn, industryColumn, educationColumn;

    @FXML
    private TableColumn<Substitute, Integer> zipcodeColumn, ageColumn, salaryColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button overwrite;

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private String activeFile;
    private boolean readFromCSV;
    private AlertBox alert;
    private ErrorBox error;

    /* ----------------- Required Controller Methods ------------------------------*/

    @Override
    public void initialize() {
        data = tableView.getItems();
        activeFile = ActivePaths.getSubstituteJOBJPath();

       readData(activeFile);
        setFiltering();
    }

    @Override
    public void refresh() {
        setActiveFile();
        data.clear();
        readData(activeFile);
    }

    @Override
    public void exit() {
    }

    /* --------------------------------- Misc Methods ------------------------------*/

    @FXML
    private void switchToCSV(ActionEvent event) {
        if (readFromCSV) {
            alert = new AlertBox("Already reading from CSV!", "File not changed");
        } else {
            readFromCSV = true;
            refresh();
        }
    }

    @FXML
    private void switchToJOBJ(ActionEvent event) {
        if (!readFromCSV) {
            alert = new AlertBox("Already reading from JOBJ!", "File not changed");
        } else {
            readFromCSV = false;
            refresh();
        }
    }

    private void readData(String activeFile) {
        try {
            data.addAll(ReaderThreadStarter.startReader(activeFile));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setActiveFile() {
        if (readFromCSV) {
            activeFile = ActivePaths.getAvailablePositionCSVPath();
        } else {
            activeFile = ActivePaths.getAvailablePositionJOBJPath();
        }
    }

    @FXML
    private void cancel() {
        sceneManager.getCurrentPopUpStage().close();
        sceneManager.setCurrentPopUpStage(null);
    }

    /* ------------------------------------------ TableView Methods ------------------------------------------------*/

    private void setFiltering() {
        FilteredList<Substitute> filteredData = new FilteredList<>(data, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aSubstitute -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                int intFilter = -1; //set to a negative value as we don't allow negative values in the datafields. Won't give a match.

                try {
                    intFilter = Integer.parseInt(lowerCaseFilter);
                } catch (NumberFormatException e) {
                    //If the String can't  be parsed to an int, don't change the intFilter.
                } //TODO Se om man finner en bedre løsning for å filtrere int-verdier

                if (aSubstitute.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (aSubstitute.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (aSubstitute.getZipcode() == intFilter) {
                    return true;
                } else return aSubstitute.getAddress().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Substitute> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
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
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS,2,3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
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
