package com.logic.utilities.exceptions;

/**
 * <h1>InvalidPhoneNumberException/h1>
 *
 * Exception to be thrown when phonenumber has been given on the wrong format.
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */

public class InvalidPhonenumberException extends Exception {
    public InvalidPhonenumberException(String msg) {
        super(msg);
    }
}
