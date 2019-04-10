package com.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {

    @FXML
    Label label;

    @FXML
    Label userCreation;

    @FXML
    void validate() {

    }

    @FXML
    public void confirm() {
        if(!userCreation.isVisible()) {
            userCreation.setVisible(true);
        }
        userCreation.setTextFill(Color.GREEN);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
