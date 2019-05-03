package com.logic.utilities.exceptions;

/**
 * <h1>CSVParseException</h1>
 *
 * DESCRIPTION
 *
 * @author Mathias Lund Ahrn
 * @since 26-04-2019
 */

//TODO Describe when this is thrown in JavaDocs
public class CSVParseException extends Exception {

    public CSVParseException(String msg) {
        super(msg);
    }
}
