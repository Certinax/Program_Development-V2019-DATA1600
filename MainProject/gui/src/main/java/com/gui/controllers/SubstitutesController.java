package com.gui.controllers;

import com.data.client.Substitute;
import com.logic.concurrency.CSVWriterThreadStarter;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class SubstitutesController implements Controller {

    @FXML
    private TableView<Substitute> tableView;

    @FXML
    private ObservableList<Substitute> data;

    @FXML
    private TableColumn<Substitute, String> fnameColumn, lnameColumn, addressColumn;

    @FXML
    private TextField filterField;


    @FXML
    private void initialize() {

        //TODO DISSE KAN UTKOMMENTERES NÅR FILLESEREN ER FERDIG IMPLEMENTERT
       // ArrayList<Substitute> dataFromFile = ReaderCaller.readCSVSubstitutesInThread();
        data = tableView.getItems();


        //TODO DISSE KAN UTKOMMENTERES NÅR FILLESEREN ER FERDIG IMPLEMENTERT
      /*  for (Substitute substituteFromFile : dataFromFile) {
            data.add(new Substitute(substituteFromFile.getUsername(), substituteFromFile.getLastname(), subtsituteFromFile.getEmail()));
        } */

        setFiltering();
        tableView.setEditable(true);
        setAddressColumnEditable();
        setLnameColumnEditable();
        setUnameColumnEditable();
    }

    @FXML
    private void delete() {
        data.remove(tableView.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void save(ActionEvent event) {
        CSVWriterThreadStarter.startWriter(data, "substitutes.csv", false);
    }


    @Override
    public void exit() {
        CSVWriterThreadStarter.startWriter(data, "substitutes.csv", false);
    }

    /* ------------------------------------------ TableView Methods ------------------------------------------------*/

    private void setFiltering() {
        FilteredList<Substitute> filteredData = new FilteredList<>(data, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(substituteUser -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (substituteUser.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (substituteUser.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return substituteUser.getAddress().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<Substitute> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setUnameColumnEditable() {
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
}
