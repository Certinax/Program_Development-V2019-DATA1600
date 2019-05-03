package com.logic.utilities.exceptions;

/**
 * <h1>EmptyStringException/h1>
 *
 * Exception to be thrown when a string is empty.
 *
 * @author Candidate 730
 * @since 15-04-2019
 */

public class EmptyStringException extends RuntimeException {

    public EmptyStringException(String msg) {
        super(msg);
    }
}
