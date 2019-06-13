package com.gui.alertBoxes;

import javafx.scene.control.Alert;

/**
 * <h1>Confirmation Box</h1>
 *
 * An alert box for getting confirmations from the user.
 *
 * @author Fredrik Pedersen
 * @since 03-05-2019
 */

public class ConfirmationBox extends Alert {

    public ConfirmationBox(String contentText, String header) {
        super(AlertType.CONFIRMATION);
        this.setHeaderText(header);
        this.setContentText(contentText);
        this.setTitle("You sure?");
    }
}
