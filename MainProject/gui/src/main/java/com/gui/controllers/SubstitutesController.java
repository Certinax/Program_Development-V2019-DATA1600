package com.gui.controllers;

import com.data.client.SortingTemplates;
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

import java.util.ArrayList;

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
        data = tableView.getItems();

        //TODO DISSE KAN UTKOMMENTERES NÅR FILLESEREN ER FERDIG IMPLEMENTERT
        //data.addAll(ReaderCaller.readCSVSubstitutesInThread());

        /* ------------------------------ Testing Code - TO BE REMOVED ------------------------------------------- */

        //TODO DISSE KONSTRUKTØRNE OG EKSTRA ARRAYLISTENE FJERNES NÅR LESEREN ER KLAR!
        ArrayList<String> job = new ArrayList<>();
        job.add("asdaasd");
        ArrayList<String> education = new ArrayList<>();
        education.add("asd");
        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("aisd");
        ArrayList<String> ref = new ArrayList<>();
        ref.add("asdasd");

        Substitute sub1 = new Substitute("Ole", "Kristiansen", "Hovligata 12", 1121, "Oslo",
                1232123, 22, 22, education, jobxp, job,  ref);

        Substitute sub2 = new Substitute("Ole", "Kristiansen", "Hovligata 12", 1121, "Oslo",
                1232123, 22, 22, education, jobxp, job,  ref);

        ArrayList<Substitute> dataFromFile = new ArrayList<>();
        dataFromFile.add(sub1);
        dataFromFile.add(sub2);
        data.addAll(dataFromFile);

        /* --------------------------------------------------- */

        tableView.setEditable(true);
        setFiltering();
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
        try {
            CSVWriterThreadStarter.startWriter(data, "resources/substitutes.csv", false, SortingTemplates.substituteTemplate());
        } catch (InterruptedException e) {
        e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
    }
    }


    @Override
    public void exit() {

        try {
            CSVWriterThreadStarter.startWriter(data, "resources/substitutes.csv", false, SortingTemplates.substituteTemplate());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        }
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
