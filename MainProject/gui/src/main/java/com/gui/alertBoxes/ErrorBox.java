package com.gui.alertBoxes;

import javafx.scene.control.Alert;

/**
 * <h1>Error Box</h1>
 *
 * An error box for displaying alerts to the user.
 *
 * @author Fredrik Pedersen
 * @since 01-05-2019
 */

public class ErrorBox extends Alert{

    public ErrorBox(String contentText, String header) {
        super(AlertType.ERROR);
        this.setHeaderText(header);
        this.setContentText(contentText);
        this.setTitle("Error");
        this.show();
    }
}
