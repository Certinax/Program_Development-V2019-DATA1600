package com.gui.controllers;

import com.logic.customNodes.IntField;
import com.logic.customNodes.PostNumberField;
import com.logic.exceptions.InvalidAddressException;
import com.logic.people.infoclasses.Address;
import com.logic.people.infoclasses.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static java.lang.System.err;

public class WelcomeScreenController {

    @FXML
    private IntField intfield;

    @FXML
    private PostNumberField postnumberfield;

    @FXML
    private TextField streetnamefield;

    @FXML
    public void createAddress(ActionEvent event) {
        Address address = null;
        int postnumber = Integer.parseInt(postnumberfield.getText());
        String streetname = streetnamefield.getText();
        System.out.println(postnumber);
        System.out.println(streetname);

        try {
            address = new Address(streetname, postnumber);
        } catch (InvalidAddressException e) {
            err.println(e.getMessage());
        }

        System.out.println(address);

    }
}
