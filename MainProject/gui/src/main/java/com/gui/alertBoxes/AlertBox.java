package com.gui.alertBoxes;

import javafx.scene.control.Alert;

public class AlertBox {

    public AlertBox(String contentText, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.setTitle("Alert");
        alert.show();
    }
}
