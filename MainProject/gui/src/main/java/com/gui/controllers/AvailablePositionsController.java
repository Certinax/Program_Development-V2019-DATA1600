package com.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AvailablePositionsController implements Controller {

    /**
     * <h2>goHome</h2>
     *
     * Method for taking the user to the homescreen when they click the button on the welcome screen
     *
     * @author Fredrik Pedersen
     * @since 08-04-2019
     * @param event - The Button is clicked
     * @throws IOException - if HomeScreen.fxml is not found or loaded properly
     */
    @FXML
    private void goHome(ActionEvent event) throws IOException {
        Parent home_screen_parent = FXMLLoader.load(getClass().getResource("/com/gui/fxml/HomeScreen.fxml"));
        Scene home_screen_scene = new Scene(home_screen_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_screen_scene);
        app_stage.show();
    }

    @Override
    public void exit() {

    }
}
