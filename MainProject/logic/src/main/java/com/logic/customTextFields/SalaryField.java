package com.logic.customTextFields;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * <h1>IntField</h1>
 * A custom TextField that only accepts 0 to 3 integers as input value
 *
 * @author Fredrik Pedersen
 * @since 27-03-2019
 */

public class SalaryField extends TextField {

    /**
     * Constructor that inherits everything from the TextField-class, using an eventlistener to make
     * sure the textfield only accepts 0-3 integers as input value
     */
    public SalaryField() {
        super();

        this.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]{0,3}")) {
                this.setText(oldValue);
            }
        });
    }
}