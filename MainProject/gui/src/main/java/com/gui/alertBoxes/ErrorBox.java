package com.gui.alertBoxes;

import javafx.scene.control.Alert;

public class ErrorBox {

    private Alert alert;

    public ErrorBox(String contentText, String header) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.setTitle("Error");
    }

    public void show() {
        alert.show();
    }
}
