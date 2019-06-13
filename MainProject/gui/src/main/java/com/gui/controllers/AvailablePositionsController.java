package com.gui.controllers;

import com.data.work.AvailablePosition;
import com.gui.alertBoxes.ConfirmationBox;
import com.gui.alertBoxes.InformationBox;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.DataPasser;
import com.logic.utilities.exceptions.ExtraStageException;
import com.logic.utilities.exceptions.NoPrimaryStageException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Available Positions Controller</h1>
 *
 * Controller class for controlling the available positions view.
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */

public class AvailablePositionsController implements Controller {

    @FXML
    private TableView<AvailablePosition> tableView;

    @FXML
    private ObservableList<AvailablePosition> tableData; //List containing all positions who are hava available = true

    @FXML
    private TableColumn<AvailablePosition, String> workplaceColumn, durationColumn;

    @FXML
    private TableColumn<AvailablePosition, Integer> salaryColumn;

    @FXML
    private TextField filterField;

    private ArrayList<AvailablePosition> allData; //List containing all positions
    private SceneManager sceneManager = SceneManager.INSTANCE;
    private boolean readFromCSV = false;
    private String activeFile; //The currently selected CSV or JOBJ file to read and write from.
    private InformationBox alert;
    private ErrorBox error;
    private ConfirmationBox confirm;

    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        activeFile = ActivePaths.getAvailablePositionJOBJPath();
        tableData = tableView.getItems();
        allData = new ArrayList<>();

        readData(activeFile);

        setFiltering();
        setWorkplaceColumnEditable();
        setSalaryColumnEditable();
        setDurationColumnEditable();
    }

    @Override
    public void refresh() { //Method for refreshing the data shown in the TableView
        setActiveFile();
        allData.clear();
        tableData.clear();
        readData(activeFile);
    }

    @Override
    public void exit() { //Saves all Positions data to file before leaving the view
        ObservableList<AvailablePosition> toFile = FXCollections.observableArrayList();
        toFile.addAll(allData);
        try {
            WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionJOBJPath(), false);
            WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionCSVPath(), false);
        } catch (InterruptedException e) {
            error = new ErrorBox("The program was interrupted while saving data!", "Could not write to file");
        }
    }

    @Override
    public void updateDataFromDataPasser() {
        //Method for updating the data in TableData and AllData after matching a Substitute with an available position
        AvailablePosition positionFromDataPasser = (AvailablePosition)DataPasser.getData();

        for (int i = 0; i < allData.size(); i++) { //Replaces the AvailablePosition object in AllData with the new one.
            if (positionFromDataPasser.getAvailablePositionId().equals(allData.get(i).getAvailablePositionId())) {
                allData.set(i,positionFromDataPasser);
                break;
            }
        }

        if (positionFromDataPasser.isAvailable()) {
            for (int i = 0; i < tableData.size(); i++) { //If the position is still available after being matched with a subsitute, replace it in TableData
                if (positionFromDataPasser.getAvailablePositionId().equals(tableData.get(i).getAvailablePositionId())) {
                    tableData.set(i, positionFromDataPasser);
                    break;
                }
            }

        } else {
            for (int i = 0; i < tableData.size(); i++) { //If the position is no longer available, remove it from the TableData
                if (positionFromDataPasser.getAvailablePositionId().equals(tableData.get(i).getAvailablePositionId())) {
                    tableData.remove(i);
                    break;
                }
            }
        }
    }

    /* ------------------------------------------- Misc Methods -----------------------------------------------------*/

    @FXML
    private void matchSubstitute() { //Method sending the selected Position object to the DataPasser and opening a new pop-up stage for matching Positions with substitutes
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            alert = new InformationBox("Please select an available position \nbefore trying to match with a substitute!", "No position selected");
        } else {
            try {
                DataPasser.setData(tableView.getSelectionModel().getSelectedItem());
                sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.MATCHSUBSTITUTE, 1, 1);
            } catch (NoPrimaryStageException | ExtraStageException e) {
                error = new ErrorBox(e.getMessage(), "Can't open new window");
            }
        }
    }

    private void readData(String activeFile) { //Method for reading Positions from file to AllData, Available Positions are added to TableData
        try {
            allData.addAll(ReaderThreadStarter.startReader(activeFile));
            for (AvailablePosition anAllData : allData) {
                if (anAllData.isAvailable()) {
                    tableData.add(anAllData);
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            error = new ErrorBox("Reading failed", "Couldn't read file");
        }
    }

    @FXML
    private void save(ActionEvent event) { //Method for saving the current AllData-list to file
        confirm = new ConfirmationBox("Sure you want to save all data to file?", "Save?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            ObservableList<AvailablePosition> toFile = FXCollections.observableArrayList();
            toFile.addAll(allData);
            try {

                WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionJOBJPath(), false);
                WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionCSVPath(), false);
            } catch (InterruptedException e) {
                error = new ErrorBox("The program was interrupted while saving data!", "Could not write to file");
            }
        }
    }

    @FXML
    private void switchToCSV(ActionEvent event) { //Method for making the TableView get it's data from the selected CSV-file
        if (readFromCSV) {
            alert = new InformationBox("Already reading from CSV!", "File not changed");
        } else {
            readFromCSV = true;
            refresh();
        }
    }

    @FXML
    private void switchToJOBJ(ActionEvent event) { //Method for makin the Tableview get it's data from the selected JOBJ-file
        if (!readFromCSV) {
            alert = new InformationBox("Already reading from JOBJ!", "File not changed");
        } else {
            readFromCSV = false;
            refresh();
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
    private void showInfo(){ //Method for passing the currently selected object to a new pop-up window, showin detailed information
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            try {
                DataPasser.setData(tableView.getSelectionModel().getSelectedItem());
                sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.POSITIONINFO,1,1);
            } catch (NoPrimaryStageException | ExtraStageException e) {
                error = new ErrorBox(e.getMessage(), "Can't open new window");
            }
        }
    }

    /* ------------------------------------------ TableView Methods ------------------------------------------------*/


    @FXML
    private void delete() {
        confirm = new ConfirmationBox("Sure you want to delete this position?", "Delete?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            for (int i = 0; i < allData.size(); i++) {
                if (allData.get(i).getAvailablePositionId().equals(tableView.getSelectionModel().getSelectedItem().getAvailablePositionId())) {
                    allData.remove(tableView.getSelectionModel().getSelectedItem());
                }
            }
            tableData.remove(tableView.getSelectionModel().getSelectedItem());

            ObservableList<AvailablePosition> toFile = FXCollections.observableArrayList();
            toFile.addAll(allData);
            try {

                WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionJOBJPath(),false);
                WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionCSVPath(), false);
            } catch (InterruptedException e) {
                error = new ErrorBox("The program was interrupted while saving data!", "Could not write to file");
            }
        }
    }

    private void setFiltering() {
        FilteredList<AvailablePosition> filteredData = new FilteredList<>(tableData, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(anAvailablePosition -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String stringFilter = newValue.toLowerCase();
                int intFilter;

                try {
                    intFilter = Integer.parseInt(stringFilter);
                } catch (NumberFormatException e) {
                    intFilter = -1; //set to a negative value as we don't allow negative values in the datafields. Won't give a match.
                }

                if (anAvailablePosition.getContactInfo().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getEmployerName().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getWorkplace().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getPositionType().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getIndustry().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getDuration().toLowerCase().contains(stringFilter)){
                    return true;
                } else return anAvailablePosition.getSalary() == intFilter;
            });
        });

        SortedList<AvailablePosition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setSalaryColumnEditable() {
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        salaryColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, Integer> t) -> t.getRowValue().setSalary(t.getNewValue()));
    }

    private void setDurationColumnEditable() {
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, String> t) -> t.getRowValue().setPositionType(t.getNewValue()));
    }

    private void setWorkplaceColumnEditable() {
        workplaceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        workplaceColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, String> t) -> t.getRowValue().setPositionType(t.getNewValue()));
    }

    /* ------------------------------------------ Menu Methods ----------------------------------------------*/

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
    private void goToEmployers(ActionEvent event) {
        sceneManager.changeScene(SceneName.EMPLOYERS);
    }

    @FXML
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS, 2, 3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
    }
}
