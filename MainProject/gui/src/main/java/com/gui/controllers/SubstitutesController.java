package com.gui.controllers;

import com.data.clients.Substitute;
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
import java.util.concurrent.ExecutionException;

//TODO Write JavaDocs!
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

    /* --------------------------------- Required Controller Methods ------------------------------------*/

    @Override
    public void initialize() {
        data = tableView.getItems();
        activeFile = ActivePaths.getSubstituteJOBJPath();

        try {
            data.addAll(ReaderThreadStarter.startReader(activeFile)); //TODO Should this read from CSV or JOBJ?
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
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
            data.addAll(ReaderThreadStarter.startReader(activeFile)); //TODO Should this read from CSV or JOBJ?
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
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
        data.remove(tableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void save(ActionEvent event) {
      try {
          WriterThreadStarter.startWriter(data, ActivePaths.getSubstituteJOBJPath());
          WriterThreadStarter.startWriter(data, ActivePaths.getSubstituteCSVPath());
        } catch (InterruptedException e) {
            error = new ErrorBox("Couldn't write to file because" + e.getMessage(), "Couldn't write to file");
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

    private void setZipCodeColumnEditable() { //TODO Kolonner som er definert med Integers kræsjer dersom man prøver å skrive inn andre tegn. Trenger korrekt feilhåndtering.
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
