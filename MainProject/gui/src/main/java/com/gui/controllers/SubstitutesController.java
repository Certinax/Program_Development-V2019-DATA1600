package com.gui.controllers;

import com.data.clients.Substitute;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.utilities.FilePaths;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import java.util.concurrent.ExecutionException;

//TODO Write JavaDocs!
public class SubstitutesController implements Controller {

    @FXML
    private TableView<Substitute> tableView;

    @FXML
    private ObservableList<Substitute> data;

    @FXML
    private TableColumn<Substitute, String> fnameColumn, lnameColumn, addressColumn, cityColumn,
            industryColumn;

    @FXML
    private TableColumn<Substitute, Integer> zipcodeColumn, ageColumn, salaryColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button overwrite;


    @FXML
    private void initialize() {
        data = tableView.getItems();

        try {
            data.addAll(ReaderThreadStarter.startReader(FilePaths.SUBSTITUTESCSV.toString())); //TODO Should this read from CSV or JOBJ?
            System.out.println(data);
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

    @FXML
    private void delete() {
        data.remove(tableView.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void save(ActionEvent event) {
      try {
          WriterThreadStarter.startWriter(data,"resources/substitutes.csv");
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        }
    }


    @Override
    public void exit() {

       /* try {
            CSVWriterThreadStarter.startWriter(data, "resources/substitutes.csv", false, SortingTemplates.substituteTemplate());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        } */
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

}
