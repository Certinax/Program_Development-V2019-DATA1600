package com.gui.controllers;

import com.data.clients.Substitute;
import com.data.work.AvailablePosition;
import com.gui.alertBoxes.InformationBox;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.DataPasser;
import com.logic.utilities.exceptions.AvailablePositionException;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
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
    private AvailablePosition position;
    private InformationBox alert;
    private ErrorBox error;

    /* ----------------- Required Controller Methods ------------------------------*/

    @Override
    public void initialize() {
        data = tableView.getItems();
        activeFile = ActivePaths.getSubstituteJOBJPath();

       readData(activeFile);
       setFiltering();
       position = (AvailablePosition) DataPasser.getData();
    }

    @Override
    public void refresh() {
        setActiveFile();
        data.clear();
        readData(activeFile);
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    @Override
    public void exit() {
    }

    /* --------------------------------- Misc Methods ------------------------------*/

    @FXML
    private void match() {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            alert = new InformationBox("Please select an item from the list", "No substitute selected");
        } else  {
            ArrayList<String> applicants = position.getApplicants();
            applicants.add(tableView.getSelectionModel().getSelectedItem().getSubstituteId());

            try {
                position.setApplicants(applicants);
            } catch (AvailablePositionException e) {
                error = new ErrorBox("This position can't have more applicants!", "Too many applicants");
            }

            DataPasser.setData(position);
            FXMLLoader loader = sceneManager.getCurrentLoader();
            Controller activeController = loader.getController();
            activeController.updateDataFromDataPasser();
            cancel();
        }
    }

    @FXML
    private void cancel() {
        sceneManager.getCurrentPopUpStage().close();
        sceneManager.setCurrentPopUpStage(null);
    }

    @FXML
    private void switchToCSV(ActionEvent event) {
        if (readFromCSV) {
            alert = new InformationBox("Already reading from CSV!", "File not changed");
        } else {
            readFromCSV = true;
            refresh();
        }
    }

    @FXML
    private void switchToJOBJ(ActionEvent event) {
        if (!readFromCSV) {
            alert = new InformationBox("Already reading from JOBJ!", "File not changed");
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
}
