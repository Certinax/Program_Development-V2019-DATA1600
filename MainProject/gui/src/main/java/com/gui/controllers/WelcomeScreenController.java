package com.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Date;


public class WelcomeScreenController {

    @FXML
    private DatePicker datepicker;

    @FXML
    private void printdate(ActionEvent event) {
        LocalDate d = datepicker.getValue();
        System.out.println(d);
    }
}
