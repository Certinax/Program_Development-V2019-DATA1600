package com.logic.customTextFields;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>NameField</h1>
 * A custom TextField that only accepts 0 to 100 characters as input value
 *
 * @author Fredrik Pedersen
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
            if (!newValue.matches("[A-Za-z]{0,100}( [A-Za-z]{1,100}){0,3}")) {
                this.setText(oldValue);
            }
        });
    }
}
