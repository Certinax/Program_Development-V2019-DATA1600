package com.gui.controllers;

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
import java.util.Set;

public class SubstitutesController implements Controller {


/*    @FXML
    private TableView<SubstituteUser> tableView;

    @FXML
    private ObservableList<SubstituteUser> data;

    @FXML
    private TableColumn<SubstituteUser, String> unameColumn, lnameColumn, mailColumn;

    @FXML
    private TextField usernameField, lastnameField, emailField, filterField;


    @FXML
    private void initialize() {
        ArrayList<SubstituteUser> dataFromFile = ReaderCaller.readCSVSubstitutesInThread();
        data = tableView.getItems();

        for (SubstituteUser subtituteFromFile : dataFromFile) {
            data.add(new SubstituteUser(subtituteFromFile.getUsername(), subtituteFromFile.getLastname(), subtituteFromFile.getEmail()));
        }

        setFiltering();
        tableView.setEditable(true);
        setMailColumnEditable();
        setLnameColumnEditable();
        setUnameColumnEditable();
    }

    @FXML
    private void addSubstitute(ActionEvent event) {
        SubstituteUser newData = new SubstituteUser(usernameField.getText(), lastnameField.getText(), emailField.getText()); //Oppretter Substituteobjekt
        data.add(newData); //legger til Substitute-objektet i GUIet
        Thread writerThread = new Thread(new CSVWriterThread(newData, true)); //skriver det originale Substitute-objektet til fil
        writerThread.start();

        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        usernameField.setText("");
        lastnameField.setText("");
        emailField.setText("");
    }

    @FXML
    private void delete() {
        data.remove(tableView.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void overwrite(ActionEvent event) {
        Thread writerThread = new Thread(new CSVWriterThread(data, false));
        writerThread.start();

        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println(threadSet.size());
    }
    //Denne vil ikke ha noen funksjon i dette prosjektet uten hovedprosjektets SceneManager.
    //Tanken er at når scenene lukkes skal den overskrive all dataen i filen med det som vises i GUIet.
    public void exit() {
        Thread writerThread = new Thread(new CSVWriterThread(data, false));
        writerThread.start();

        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ------------------------------------------ TableView Methods ------------------------------------------------*/

 /*   private void setFiltering() {
        FilteredList<SubstituteUser> filteredData = new FilteredList<>(data, p -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(substituteUser -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (substituteUser.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (substituteUser.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return substituteUser.getEmail().toLowerCase().contains(lowerCaseFilter);
            });
        });

        SortedList<SubstituteUser> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
    }

    private void setUnameColumnEditable() {
        unameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        unameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<SubstituteUser, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setUsername(t.getNewValue()));
    }

    private void setLnameColumnEditable() {
        lnameColumn.setCellFactory(TextFieldTableCell.<SubstituteUser>forTableColumn());
        lnameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<SubstituteUser, String> t) -> {
                    ((SubstituteUser) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLastname(t.getNewValue());
                });
    }

    private void setMailColumnEditable() {
        mailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mailColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<SubstituteUser, String> t) -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setEmail(t.getNewValue()));
    }
} */


    @Override
    public void exit() {
    }
}
