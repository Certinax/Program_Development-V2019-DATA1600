package com.logic.utilities.exceptions;

/**
 * <h1>SerilizationException/h1>
 *
 * Exception to be thrown if an object can't be serialized.
 *
 * @author Mathias Lund Ahrn
 * @since 15-04-2019
 */

public class SerializationException extends Exception {

    public SerializationException(String msg) {
        super(msg);
    }
}
