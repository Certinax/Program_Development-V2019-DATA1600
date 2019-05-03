package com.logic.customTextFields;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>NameField</h1>
 *
 * A custom TextField that only accepts 0 to 100 characters as input value
 *
 * @author Candidate 730
 * @since 08-04-2019
 */

public class NameField extends TextField {

    /**
     * Constructor that inherits everything from the TextField-class, using an eventlistener to make
     * sure the textfield only accepts 0-100 characters as input value
     */
    public NameField() {
        super();

        this.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("^[a-zA-Z]{0,100}( [a-zA-Z]{0,100}){0,2}$")) {
                this.setText(oldValue);
            }
        });
    }
}
