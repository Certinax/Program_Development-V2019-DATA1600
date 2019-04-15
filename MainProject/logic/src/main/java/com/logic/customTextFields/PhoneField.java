package com.logic.customTextFields;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>PhoneField</h1>
 * A custom TextField that only accepts 0 to 8 integers as input value
 *
 * @author Fredrik Pedersen
 * @since 08-04-2019
 */


public class PhoneField extends TextField {

    /**
     * Constructor that inherits everything from the TextField-class, using an eventlistener to make
     * sure the textfield only accepts 0-8 integers as input value
     */
    public PhoneField() {
        super();

        this.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]{0,8}")) {
                this.setText(oldValue);
            }
        });

        this.setPromptText("8-digit phonenumber e.g 99106201");
    }
}
