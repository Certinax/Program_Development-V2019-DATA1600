package com.gui.controllers;

import com.data.clients.Employer;
import com.gui.alertBoxes.ConfirmationBox;
import com.gui.alertBoxes.ErrorBox;
import com.gui.alertBoxes.InformationBox;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Employers Controller</h1>
 *
 * Controller for the Employers TableView
 *
 * @author Candidate 730
 * @since 02-05-2019
 */

public class EmployersController implements Controller {

    @FXML
    private TableView<Employer> tableView;

    @FXML
    private ObservableList<Employer> tableData;

    @FXML
    private TableColumn<Employer, String> nameColumn, emailColumn;

    @FXML
    private TableColumn<Employer, Integer> phoneColumn;

    @FXML
    private TextField filterField;

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private boolean readFromCSV = false;
    private String activeFile; //The currently selected CSV or JOBJ file to read and write from.
    private InformationBox alert;
    private ErrorBox error;
    private ConfirmationBox confirm;

    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        activeFile = ActivePaths.getEmployerJOBJPath();
        tableData = tableView.getItems();

        readData(activeFile);

        setFiltering();
        setNameColumnEditable();
        setEmailColumnEditable();
        setPhoneColumnEditable();
    }

    @Override
    public void refresh() { //Method to
        setActiveFile();
        tableData.clear();
        readData(activeFile);
    }

    @Override
    public void exit() {
        ObservableList<Employer> toFile = FXCollections.observableArrayList();
        toFile.addAll(tableData);
        try {
            WriterThreadStarter.startWriter(toFile, ActivePaths.getEmployerJOBJPath());
            WriterThreadStarter.startWriter(toFile, ActivePaths.getEmployerCSVPath());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        }
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    /* ------------------------------------------- Misc Methods -----------------------------------------------------*/

    private void readData(String activeFile) {
        try {
            tableData.addAll(ReaderThreadStarter.startReader(activeFile));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save(ActionEvent event) {
        confirm = new ConfirmationBox("Sure you want to save all data to file?", "Save?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                WriterThreadStarter.startWriter(tableData, ActivePaths.getAvailablePositionJOBJPath());
                WriterThreadStarter.startWriter(tableData, ActivePaths.getAvailablePositionCSVPath());
            } catch (InterruptedException e) {
                e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
            }
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
            activeFile = ActivePaths.getEmployerCSVPath();
        } else {
            activeFile = ActivePaths.getEmployerJOBJPath();
        }
    }

    @FXML
    private void showInfo(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            try {
                DataPasser.setData(tableView.getSelectionModel().getSelectedItem());
                sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.EMPLOYERINFO,1,1);
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
            tableData.remove(tableView.getSelectionModel().getSelectedItem());

            try {
                WriterThreadStarter.startWriter(tableData, ActivePaths.getEmployerCSVPath());
                WriterThreadStarter.startWriter(tableData, ActivePaths.getEmployerJOBJPath());
            } catch (InterruptedException e) {
                e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
            }
        }
    }

    private void setFiltering() {
        FilteredList<Employer> filteredData = new FilteredList<>(tableData, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(anEmployer -> {
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

                if (anEmployer.getName().toLowerCase().contains(stringFilter)
                        || anEmployer.getEmail().toLowerCase().contains(stringFilter)){
                    return true;
                } else return anEmployer.getPhoneNumber() == intFilter;
            });
        });

        SortedList<Employer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setPhoneColumnEditable() { //TODO Kolonner som er definert med Integers gir en NumberFormatException når annet skrives inn. Håndter dette!
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        phoneColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, Integer> t) -> t.getRowValue().setPhoneNumber(t.getNewValue()));
    }

    private void setNameColumnEditable() {
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, String> t) -> t.getRowValue().setName(t.getNewValue()));
    }

    private void setEmailColumnEditable() {
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Employer, String> t) -> t.getRowValue().setEmail(t.getNewValue()));
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
