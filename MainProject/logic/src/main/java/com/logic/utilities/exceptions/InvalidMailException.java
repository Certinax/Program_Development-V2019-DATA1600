package com.logic.utilities.exceptions;

/**
 * <h1>InvalidMailException/h1>
 *
 * Exception to be thrown when an emailaddress has been given on the wrong format.
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */

public class InvalidMailException extends Exception {
    public InvalidMailException(String msg) {
        super(msg);
    }
}
