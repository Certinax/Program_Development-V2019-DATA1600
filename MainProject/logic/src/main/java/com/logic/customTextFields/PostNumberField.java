package com.logic.customTextFields;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>PostNumberField</h1>
 * A custom TextField that only accepts 0 to 4 integers as input value, meant for taking in postnumbers
 *
 * @author Fredrik Pedersen
 * @since 08-04-2019
 */
public class PostNumberField extends TextField {
    /**
     * Constructor that inherits everything from the TextField-class, using an eventlistener to make
     * sure the textfield only accepts 0-4 integers as input value
     */
    public PostNumberField() {
        super();

        this.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]{0,4}")) {
                this.setText(oldValue);
            }
        });

        this.setPromptText("Postnumber e.g 1930");
    }
}
