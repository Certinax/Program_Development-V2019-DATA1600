package com.logic.utilities.exceptions;

/**
 * <h1>InvalidPhonenumberException/h1>
 *
 * Exception to be thrown when a phonenumber has been given on the wrong format.
 *
 * @author Candidate 730
 * @since 15-04-2019
 */

public class InvalidPostnumberException extends Exception {
    public InvalidPostnumberException(String msg) {
        super(msg);
    }
}
