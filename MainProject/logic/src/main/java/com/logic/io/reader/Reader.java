package com.logic.io.reader;

import com.logic.utilities.exceptions.CSVParseException;

import java.io.IOException;
import java.util.ArrayList;

public interface Reader {

    <T> ArrayList<T> readObjects(String path) throws IOException, ClassNotFoundException, CSVParseException;
}
