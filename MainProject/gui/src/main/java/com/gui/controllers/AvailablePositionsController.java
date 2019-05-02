package com.gui.controllers;

import com.data.work.AvailablePosition;
import com.gui.alertBoxes.AlertBox;
import com.gui.alertBoxes.ErrorBox;
import com.gui.scene.SceneManager;
import com.gui.scene.SceneName;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.filePaths.ActivePaths;
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

//TODO Write JavaDocs!
public class AvailablePositionsController implements Controller {

    @FXML
    private TableView<AvailablePosition> tableView;

    @FXML
    private ObservableList<AvailablePosition> data;

    @FXML
    private TableColumn<AvailablePosition, String> workplaceColumn;

    @FXML
    private TableColumn<AvailablePosition, Integer> durationColumn, salaryColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button matchSubstitute, save;

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private boolean readFromCSV = false;
    private String activeFile;
    private AlertBox alert;
    private ErrorBox error;

    //TODO Ha en checkbox for Available og ikke, som filtrer på den boolske verdien
    //TODO Samkjør denne controlleren med oppsettet for Active File i Substitute Controller

    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        activeFile = ActivePaths.getAvailablePositionJOBJPath();
        data = tableView.getItems();

        readData(activeFile);
        setFiltering();
        setWorkplaceColumnEditable();
        setSalaryColumnEditable();
        setDurationEditable();
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

    /* ------------------------------------------- Misc Methods -----------------------------------------------------*/

    @FXML
    private void matchSubstitute() {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            alert = new AlertBox("Please select an available position \nbefore trying to match with a substitute!", "No position selected");
        } else {
            try {
                sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.MATCHSUBSTITUTE, 1, 1);
            } catch (NoPrimaryStageException e) {
                error = new ErrorBox(e.getMessage(), "How did you even...?");
            }
        }
    }

    private void readData(String activeFile) {
        try {
            data.addAll(ReaderThreadStarter.startReader(activeFile));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save(ActionEvent event) {
        try {
            WriterThreadStarter.startWriter(data, ActivePaths.getAvailablePositionJOBJPath());
            WriterThreadStarter.startWriter(data, ActivePaths.getAvailablePositionCSVPath());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        }
    }

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

    private void setActiveFile() {
        if (readFromCSV) {
            activeFile = ActivePaths.getAvailablePositionCSVPath();
        } else {
            activeFile = ActivePaths.getAvailablePositionJOBJPath();
        }
    }

    /* ------------------------------------------ TableView Methods ------------------------------------------------*/


    @FXML
    private void delete() {
        data.remove(tableView.getSelectionModel().getSelectedItem());
    }

    private void setFiltering() {
        FilteredList<AvailablePosition> filteredData = new FilteredList<>(data, p -> true);

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

    private void setDurationEditable() { //TODO Kolonner som er definert med Integers gir en NumberFormatException når annet skrives inn. Håndter dette!
            durationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            durationColumn.setOnEditCommit(
                    (TableColumn.CellEditEvent<AvailablePosition, Integer> t) -> t.getRowValue().setSalary(t.getNewValue()));
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
        } catch (NoPrimaryStageException e) {
            System.err.println(e.getMessage());
        }
    }
}
