package com.data;

import com.data.client.Employer;
import com.logic.io.reader.ReaderCSV;
import com.logic.utilities.exceptions.CSVParseException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DataTesting {

    public static void main(String[] args) {
        ReaderCSV reader = new ReaderCSV();

        try {
            Class clazz = Class.forName("com.data.client.Substitute");
            reader.read("resources/substitutes.csv", clazz);
        } catch (ClassNotFoundException | IOException | CSVParseException e) {
            e.printStackTrace();
        }
    }
}
