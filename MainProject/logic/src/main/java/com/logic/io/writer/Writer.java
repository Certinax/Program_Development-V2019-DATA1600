package com.logic.io.writer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * <h1>Writer</h1>
 *
 * Interface for representing the CSV and JOBJ-writers and utilize the strategy design-pattern.
 *
 * @author Fredrik Pedersen
 * @since 25-04-2019
 */

public interface Writer {

    void writeObject(Object obj, String path, boolean append) throws IllegalAccessException,
    InvocationTargetException, ClassNotFoundException, IOException;

    void writeObject(Object obj, String path, boolean append, String[] sortingTemplate)
            throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, IOException;
}
