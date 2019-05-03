package com.logic.utilities.exceptions;

/**
 * <h1>CSVParseException</h1>
 *
 * Used for throwin exception when the CSV parser encounters issues.
 *
 * @author Candidate 511
 * @since 26-04-2019
 */

public class CSVParseException extends Exception {

    public CSVParseException() {}

    public CSVParseException(String msg) {
        super(msg);
    }
}
