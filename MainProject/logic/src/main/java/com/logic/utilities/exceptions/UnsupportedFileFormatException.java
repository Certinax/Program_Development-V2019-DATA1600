package com.logic.utilities.exceptions;

/**
 * <h1>UnsupportedFileFormatException/h1>
 *
 * Exception to be thrown if a user tries to read or write to a file of the wrong format
 *
 * @author Candidate 730
 * @since 26-04-2019
 */

public class UnsupportedFileFormatException extends Exception {
    public UnsupportedFileFormatException(String msg) {
        super(msg);
    }
}
