package com.logic.utilities.exceptions;

/**
 * <h1>InvalidAddressException/h1>
 *
 * Exception to be thrown when an address has been given on the wrong format.
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */


public class InvalidAddressException extends Exception {
    public InvalidAddressException(String msg) {
        super(msg);
    }
}
