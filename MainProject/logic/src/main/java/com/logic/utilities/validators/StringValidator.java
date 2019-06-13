package com.logic.utilities.validators;

import com.logic.utilities.exceptions.EmptyStringException;

/**
 * <h1>String Validator</h1>
 *
 * Class with static methods to valide Strings in the application
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */

public class StringValidator {

    private StringValidator() { //Private constructor to deter initialization
    }

    public static String requireNonNullAndNotEmpty(String input) {
        if(input == null) {
            throw new NullPointerException();
        }

        if("".equals(input)) {
            throw new EmptyStringException("The String is empty!");
        }

        return input;
    }
}
