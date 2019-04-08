package com.logic.customNodes;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>IntField</h1>
 * A custom TextField that only accepts 0 to 4 integers as input value, meant for taking in postnumbers
 *
 * @author Fredrik Pedersen
 * @since 08-04-2019
 */
public class PostNumberField extends TextField {


    /**
     * Constructor that inherits everything from the TextField-class and makes sure the textfield only accepts between
     * 0-4 integers as input value
     */
    public PostNumberField() {
        super();

        this.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]{0,4}")) {
                this.setText(oldValue);
            }
        });
    }
}
