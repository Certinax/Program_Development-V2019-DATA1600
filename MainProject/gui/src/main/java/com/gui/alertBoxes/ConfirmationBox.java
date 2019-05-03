package com.gui.alertBoxes;

import javafx.scene.control.Alert;

public class ConfirmationBox extends Alert {

    public ConfirmationBox(String contentText, String header) {
        super(AlertType.CONFIRMATION);
        this.setHeaderText(header);
        this.setContentText(contentText);
        this.setTitle("You sure?");
    }
}
