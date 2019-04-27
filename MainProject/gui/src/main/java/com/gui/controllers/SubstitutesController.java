package com.gui.controllers;

import com.data.clients.Substitute;
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


        ArrayList<String> education = new ArrayList<>();
        education.add("OsloMet");
        education.add("Software Engineering");
        education.add("2017");
        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("WikborgRein");
        jobxp.add("2017");
        jobxp.add("2019");
        ArrayList<String> ref = new ArrayList<>();
        ref.add("Kim Knudsen");
        ref.add("kimmelim@mail.com");
        ref.add("99106201");

        Substitute sub1 = new Substitute.Builder("Petter", "Olsen", "Sveitsrupveien 20", 24,
                1212, "Oslo", "Banking").salaryRequirement(220000).education(education).workExperience(jobxp).workReference(ref).build();

        Substitute sub2 = new Substitute.Builder("Victor", "Pishva", "Sveitsrupveien 20", 25,
                2007, "Oslo", "Banking").salaryRequirement(200000).education(education).workExperience(jobxp).workReference(ref).build();


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
      /*  try {
            CSVWriterThreadStarter.startWriter(data, "resources/substitutes.csv", false, SortingTemplates.substituteTemplate());
        } catch (InterruptedException e) {
            e.printStackTrace(); //TODO THIS SHOULD PRINT A MESSAGE TO THE GUI
        } */
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
