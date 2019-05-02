package com.gui.controllers;

import com.data.work.AvailablePosition;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Available Positions Controller</h1>
 *
 * Controller class for controlling the available positions view.
 *
 * @author Candidate 730
 * @since 22-04-2019
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
    public void refresh() { //Method to
        setActiveFile();
        allData.clear();
        tableData.clear();
        readData(activeFile);
    }

    @Override
    public void exit() {
        ObservableList<AvailablePosition> toFile = FXCollections.observableArrayList();
        toFile.addAll(allData);
        try {
            WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionJOBJPath());
            WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionCSVPath());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        }
    }

    @Override
    public void updateDataFromDataPasser() {
        AvailablePosition positionFromDataPasser = (AvailablePosition)DataPasser.getData();

        for (int i = 0; i < allData.size(); i++) {
            if (positionFromDataPasser.getAvailablePositionId().equals(allData.get(i).getAvailablePositionId())) {
                allData.set(i,positionFromDataPasser);
                break;
            }
        }

        if (positionFromDataPasser.isAvailable()) {
            for (int i = 0; i < tableData.size(); i++) {
                if (positionFromDataPasser.getAvailablePositionId().equals(tableData.get(i).getAvailablePositionId())) {
                    tableData.set(i, positionFromDataPasser);
                    break;
                }
            }

        } else {
            for (int i = 0; i < tableData.size(); i++) {
                if (positionFromDataPasser.getAvailablePositionId().equals(tableData.get(i).getAvailablePositionId())) {
                    tableData.remove(i);
                    break;
                }
            }
        }
    }

    /* ------------------------------------------- Misc Methods -----------------------------------------------------*/

    @FXML
    private void matchSubstitute() {
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

    private void readData(String activeFile) {
        try {
            allData.addAll(ReaderThreadStarter.startReader(activeFile));
            for (int i = 0; i < allData.size(); i++) {
                if (allData.get(i).isAvailable()) {
                    tableData.add(allData.get(i));
                }
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save(ActionEvent event) {
        ObservableList<AvailablePosition> toFile = FXCollections.observableArrayList();
        toFile.addAll(allData);
        try {

            WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionJOBJPath());
            WriterThreadStarter.startWriter(toFile, ActivePaths.getAvailablePositionCSVPath());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        }
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

    private void setActiveFile() {
        if (readFromCSV) {
            activeFile = ActivePaths.getAvailablePositionCSVPath();
        } else {
            activeFile = ActivePaths.getAvailablePositionJOBJPath();
        }
    }

    @FXML
    private void showInfo(){
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
        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getAvailablePositionId().equals(tableView.getSelectionModel().getSelectedItem().getAvailablePositionId())) {
                allData.remove(tableView.getSelectionModel().getSelectedItem());
            }
        }
        tableData.remove(tableView.getSelectionModel().getSelectedItem());
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
                } //TODO Se om man finner en bedre løsning for å filtrere int-verdier

                if (anAvailablePosition.getContactInfo().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getEmployerName().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getWorkplace().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getPositionType().toLowerCase().contains(stringFilter)
                        || anAvailablePosition.getIndustry().toLowerCase().contains(stringFilter)
                        /*|| anAvailablePosition.getDuration() == intFilter*/){
                    return true;
                } else return anAvailablePosition.getSalary() == intFilter;
            });
        });

        SortedList<AvailablePosition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setSalaryColumnEditable() { //TODO Kolonner som er definert med Integers gir en NumberFormatException når annet skrives inn. Håndter dette!
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
            sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.OPTIONS, 2, 3);
        } catch (NoPrimaryStageException | ExtraStageException e) {
            error = new ErrorBox(e.getMessage(), "Can't open new window");
        }
    }
}
