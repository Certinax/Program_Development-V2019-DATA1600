package com.logic.utilities.exceptions;

/**
 * <h1>CSVParseException</h1>
 *
 * DESCRIPTION
 *
 * @author Candidate 511
 * @since 26-04-2019
 */

//TODO Describe when this is thrown in JavaDocs
public class CSVParseException extends Exception {

    public CSVParseException() {}

    public CSVParseException(String msg) {
        super(msg);
    }
}
