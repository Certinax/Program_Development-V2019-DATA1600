package com.gui.controllers;

import com.data.clients.Substitute;
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
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Substitute Controller</h1>
 *
 * Controller for showing the substitute TableView
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */
public class SubstitutesController implements Controller {

    @FXML
    private TableView<Substitute> tableView;

    @FXML
    private ObservableList<Substitute> data;

    @FXML
    private TableColumn<Substitute, String> fnameColumn, lnameColumn, addressColumn, cityColumn;

    @FXML
    private TableColumn<Substitute, Integer> zipcodeColumn, ageColumn, salaryColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button save;

    private SceneManager sceneManager = SceneManager.INSTANCE;
    private String activeFile;
    private boolean readFromCSV = false;
    private InformationBox alert;
    private ErrorBox error;
    private ConfirmationBox confirm;

    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        data = tableView.getItems();
        activeFile = ActivePaths.getSubstituteJOBJPath();

        try {
            data.addAll(ReaderThreadStarter.startReader(activeFile));
        } catch (ExecutionException | InterruptedException e) {
            error = new ErrorBox("Can't read file!", "No reading for you");
        }

        setFiltering();
        setAddressColumnEditable();
        setLnameColumnEditable();
        setFnameColumnEditable();
        setZipCodeColumnEditable();
        setCityColumnEditable();
        setAgeColumnEditable();
        setSalaryColumnEditable();
    }

    @Override
    public void refresh() {
        activeFile = ActivePaths.getSubstituteJOBJPath();
        data.clear();

        try {
            data.addAll(ReaderThreadStarter.startReader(activeFile));
        } catch (ExecutionException | InterruptedException e) {
            error = new ErrorBox("Reading is for nerds. Crashing is so more fun!", "Could'nt read file");
        }
    }

    @Override
    public void updateDataFromDataPasser() {
    }

    @Override
    public void exit() {
    }

    /* --------------------------- Misc Methods ----------------------------------------*/

    @FXML
    private void delete() {
        confirm = new ConfirmationBox("Are you sure you want to delete " + tableView.getSelectionModel().getSelectedItem().getFirstname() +
                " " + tableView.getSelectionModel().getSelectedItem().getLastname() + "?", "Delete?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            data.remove(tableView.getSelectionModel().getSelectedItem());

            try {

                WriterThreadStarter.startWriter(data, ActivePaths.getSubstituteJOBJPath(), false);
                WriterThreadStarter.startWriter(data, ActivePaths.getSubstituteCSVPath(), false);
            } catch (InterruptedException e) {
                error = new ErrorBox("The program can't read from file", "Can't read document");
            }
        }
    }

    @FXML
    private void save(ActionEvent event) {
        confirm = new ConfirmationBox("Are you sure you want to save all data?", "Save?");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                WriterThreadStarter.startWriter(data, ActivePaths.getSubstituteJOBJPath(), false);
                WriterThreadStarter.startWriter(data, ActivePaths.getSubstituteCSVPath(), false);
            } catch (InterruptedException e) {
                error = new ErrorBox("Couldn't write to file because" + e.getMessage(), "Couldn't write to file");
            }
        }
    }

    public void readFromCSV(ActionEvent event) {
        if (readFromCSV) {
            alert = new InformationBox("Already reading from CSV!", "File not changed");
        } else {
            readFromCSV = true;
            refresh();
        }
    }

    public void readFromJOBJ(ActionEvent event) {
        if (!readFromCSV) {
            alert = new InformationBox("Already reading from JOBJ!", "File not changed");
        } else {
            readFromCSV = false;
            refresh();
        }
    }

    public void showInfo(ActionEvent event){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            try {
                DataPasser.setData(tableView.getSelectionModel().getSelectedItem());
                sceneManager.createUndecoratedStageWithScene(new Stage(), SceneName.SUBSTITUTEINFO,1,1);
            } catch (NoPrimaryStageException | ExtraStageException e) {
                error = new ErrorBox(e.getMessage(), "Can't open new window");
            }
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
                }
                if (aSubstitute.getFirstname().toLowerCase().contains(lowerCaseFilter)
                        || aSubstitute.getLastname().toLowerCase().contains(lowerCaseFilter)
                        || aSubstitute.getAddress().toLowerCase().contains(lowerCaseFilter)
                        || aSubstitute.getCity().toLowerCase().contains(lowerCaseFilter)
                        || aSubstitute.getAge() == intFilter
                        || aSubstitute.getSalaryRequirement() == intFilter){
                    return true;
                } else return aSubstitute.getZipcode() == intFilter;
            });
        });

        SortedList<Substitute> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setFnameColumnEditable() {
        fnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fnameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setFirstname(t.getNewValue()));
    }

    private void setLnameColumnEditable() {
        lnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lnameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setLastname(t.getNewValue()));
    }

    private void setAddressColumnEditable() {
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAddress(t.getNewValue()));
    }

    private void setZipCodeColumnEditable() {
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        zipcodeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setZipcode(t.getNewValue()));
    }

    private void setCityColumnEditable() {
        cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cityColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setCity(t.getNewValue()));
    }

    private void setAgeColumnEditable() {
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAge(t.getNewValue()));
    }

    private void setSalaryColumnEditable() {
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        salaryColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Substitute, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setAge(t.getNewValue()));
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
    private void goToEmployers(ActionEvent event) {
        sceneManager.changeScene(SceneName.EMPLOYERS);
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
}
