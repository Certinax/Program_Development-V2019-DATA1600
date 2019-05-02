package com.gui.alertBoxes;

import javafx.scene.control.Alert;

/**
 * <h1>Error Box</h1>
 *
 * An error box for displaying alerts to the user.
 *
 * @author Candidate 730
 * @since 01-05-2019
 */

public class ErrorBox {

    public ErrorBox(String contentText, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.setTitle("Error");
        alert.show();
    }
}
