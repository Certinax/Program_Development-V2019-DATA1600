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

public class InformationBox extends Alert {

    public InformationBox(String contentText, String header) {
        super(AlertType.INFORMATION);
        this.setHeaderText(header);
        this.setContentText(contentText);
        this.setTitle("Alert");
        this.show();
    }
}
