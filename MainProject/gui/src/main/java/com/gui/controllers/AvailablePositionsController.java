package com.gui.controllers;

import com.data.work.AvailablePosition;
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

    private SceneManager sceneManager = SceneManager.INSTANCE;

    @FXML
    private TableView<AvailablePosition> tableView;

    @FXML
    private ObservableList<AvailablePosition> data;

    @FXML
    private TableColumn<AvailablePosition, String> placeColumn, positionTypeColumn, industryColumn;

    @FXML
    private TableColumn<AvailablePosition, Integer> employerColumn, durationColumn, salaryColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button overwrite;


    @FXML
    private void initialize() {
        data = tableView.getItems();

        try {
            data.addAll(ReaderThreadStarter.startReader(ActivePaths.getAvailablePositionJOBJPath())); //TODO Should this read from CSV or JOBJ?
            System.out.println(data);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        /*setFiltering();
        setPlaceColumnEditable();
        setSalaryColumnEditable();
        setPositionColumnEditable();
        setIndustryColumnEditable();
        setEmployerColumnEditable();
        setDurationColumnEditable();*/
    }

    @FXML
    private void delete() {
        data.remove(tableView.getSelectionModel().getSelectedItem());

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


    @Override
    public void exit() {
    }

    /* ------------------------------------------ TableView Methods ------------------------------------------------*/
   /* private void setFiltering() {
        FilteredList<AvailablePosition> filteredData = new FilteredList<>(data, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(anAvailablePosition -> {
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

                if (anAvailablePosition.getWorkplace().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (anAvailablePosition.getEmployer() == intFilter) {
                    return true;
                } else return anAvailablePosition.getPositionType().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<AvailablePosition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setSalaryColumnEditable() { //TODO Kolonner som er definert med Integers kræsjer dersom man prøver å skrive inn andre tegn. Trenger korrekt feilhåndtering.
        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        salaryColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setSalary(t.getNewValue()));
    }

    private void setEmployerColumnEditable() { //TODO Kolonner som er definert med Integers kræsjer dersom man prøver å skrive inn andre tegn. Trenger korrekt feilhåndtering.
        employerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        employerColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setEmployer(t.getNewValue()));
    }

    private void setDurationColumnEditable() { //TODO Kolonner som er definert med Integers kræsjer dersom man prøver å skrive inn andre tegn. Trenger korrekt feilhåndtering.
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        durationColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, Integer> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setDuration(t.getNewValue()));
    }

    private void setPlaceColumnEditable() {
        placeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        placeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setWorkplace(t.getNewValue()));
    }

    private void setPositionColumnEditable() {
        positionTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionTypeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setPositionType(t.getNewValue()));
    }

    private void setIndustryColumnEditable() {
        industryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        industryColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<AvailablePosition, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setIndustry(t.getNewValue()));
    }

     ----------------------------------- Navigation Buttons - To Be Removed -------------------------------------


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
    private void openOptions(ActionEvent event) {
        try {
            sceneManager.createNewStageWithScene(new Stage(), SceneName.OPTIONS);
        } catch (NoPrimaryStageException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    private void setWindowedMode() {
        sceneManager.setWindowed();
    }*/
}
