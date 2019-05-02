package com.gui.alertBoxes;

import javafx.scene.control.Alert;

public class ErrorBox {

    public ErrorBox(String contentText, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.setTitle("Error");
    }
}
