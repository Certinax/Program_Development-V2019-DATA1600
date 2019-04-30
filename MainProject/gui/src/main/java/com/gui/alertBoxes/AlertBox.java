package com.gui.alertBoxes;

import javafx.scene.control.Alert;

public class AlertBox {

    private Alert alert;

    public AlertBox(String contentText, String header) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.setTitle("Alert");
    }

    public void show() {
        alert.show();
    }
}
