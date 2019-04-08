package com.logic.customTextField;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>AgeField</h1>
 * A custom TextField that only accepts 1 to 3 integers as input value
 *
 * @author Fredrik Pedersen
 * @since 08-04-2019
 */

public class AgeField extends TextField {


    /**
     * Constructor that inherits everything from the TextField-class, using an eventlistener to make
     * sure the textfield only accepts 1-3 integers as input value
     */
    public AgeField() {
        super();

        this.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]{1,3}")) {
                this.setText(oldValue);
            }
        });

        this.setPromptText("Your age, e.g 42");
    }


}
