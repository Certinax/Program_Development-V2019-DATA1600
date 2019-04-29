package com.logic.io.reader;

import com.logic.utilities.exceptions.CSVParseException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1>Reader</h1>
 *
 * Interface representing file readers
 *
 * @author Fredrik Pedersen
 * @since 26-04-2019
 */

public interface Reader {

    <T> ArrayList<T> readObjects(String path) throws IOException, ClassNotFoundException, CSVParseException;
}
