package com.logic.utilities.exceptions;

/**
 * <h1>CSVParseException</h1>
 *
 * DESCRIPTION
 *
 * @author Mathias Lund Ahrn
 * @since 26-04-2019
 */

public class CSVParseException extends Exception {

    public CSVParseException() {}

    public CSVParseException(String msg) {
        super(msg);
    }
}
