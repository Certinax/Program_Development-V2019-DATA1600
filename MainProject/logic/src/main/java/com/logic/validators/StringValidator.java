package com.logic.validators;

import com.logic.exceptions.EmptyStringException;

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

    public static boolean stringLengthIsValid(String input, int maxLength) {
        return input.length() < maxLength;
    }
}
