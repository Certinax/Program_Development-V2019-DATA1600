package com.gui.alertBoxes;

import javafx.scene.control.Alert;

/**
 * <h1>Information Box</h1>
 *
 * An alert box for displaying alerts to the user.
 *
 * @author Candidate 730
 * @since 01-05-2019
 */

public class InformationBox {

    public InformationBox(String contentText, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.setTitle("Alert");
        alert.show();
    }
}
