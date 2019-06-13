package com.logic.io.writer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <h1>WriterCSV</h1>
 *
 * This class writes objects to csv file.
 * It utilizes an template as an string array for sorting the data in desired order.
 *
 * Require that a class implements CSVWriteable interface - this is placed @ com.data.CSVWriteable,
 * this makes sure that a class has a template.
 *
 *
 * This writer uses reflection.
 *
 *
 * @author Mathias Lund Ahrn, Fredrik Pedersen
 * @since 18-04-2019
 */

public class WriterCSV implements Writer {

    private final static String DELIMITER = ";";

    public void writeObject(Object obj, String path, boolean append) throws IllegalAccessException,
            InvocationTargetException, ClassNotFoundException, IOException {

        ObservableList<Object> object = FXCollections.observableArrayList();
        object.add(obj);

        writeObjects(object, path, append);
    }

    public <T> void writeObjects(ObservableList<T> objects, String path, boolean append)
            throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, IOException {


        FileWriter filewriter = null;
        File file = new File(path);
        ArrayList<String> infoToWrite = new ArrayList<>();
        boolean header = true;

        if(append && file.length() > 0) {
            header = false;
        }

        for (Object object : objects) {
            infoToWrite.add(generateCSVInfo(object, header));
            if (header) {
                header = false;
            }
        }

        try {
            filewriter = new FileWriter(file, append);
            for (String anInfoToWrite : infoToWrite) {
                filewriter.write(anInfoToWrite);
            }
        } catch (IOException e) {
            throw new IOException("Failed to write to file");
        } finally {
            if (filewriter != null) {
                filewriter.flush();
                filewriter.close();
            }
        }

    }

    private String generateCSVInfo(Object obj, boolean header)
            throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        Objects.requireNonNull(obj);

        // Access class and template
        Class clazz = obj.getClass();
        String[] sortingTemplate = findTemplate(obj);
        boolean sorting = sortingTemplate.length > 0;

        // Generate all classes and methods and it's data
        Map<String, String> objectInfo = new HashMap<>(generateClassMethodsAndData(clazz, obj));

        // For inhertiance cases
        while (hasParent(clazz)) {
            clazz = clazz.getSuperclass();
            objectInfo.putAll(generateClassMethodsAndData(clazz, obj));
        }

        // Map that stores sorted based on a template
        Map<String, String> preparedObjectInfo;
        StringBuilder csvInfo = new StringBuilder();

        if (header) {
            csvInfo.append("Class");
            csvInfo.append(DELIMITER);
            csvInfo.append(obj.getClass().getName());
            csvInfo.append("\n");
            if (sorting) {
                preparedObjectInfo = templateSort(objectInfo, sortingTemplate);
                csvInfo.append(generateCSVStringHeader(preparedObjectInfo));
                csvInfo.append(generateCSVStringData(preparedObjectInfo));
                csvInfo.append("\n");
            } else {
                csvInfo.append(generateCSVStringHeader(objectInfo));
                csvInfo.append(generateCSVStringData(objectInfo));
                csvInfo.append("\n");
            }
        } else {
            if (sorting) {
                preparedObjectInfo = templateSort(objectInfo, sortingTemplate);
                csvInfo.append(generateCSVStringData(preparedObjectInfo));
                csvInfo.append("\n");
            } else {
                csvInfo.append(generateCSVStringData(objectInfo));
                csvInfo.append("\n");
            }
        }
        csvInfo.deleteCharAt(csvInfo.length() - 1);
        return csvInfo.toString();
    }

    private String generateCSVStringData(Map<String, String> preparedObjectInfo) {
        StringBuilder sb = new StringBuilder();

        // Produces a String corresponding to the object values
        for (Map.Entry<String, String> entry : preparedObjectInfo.entrySet()) {
            sb.append(entry.getValue());
            sb.append(DELIMITER);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        return sb.toString();
    }

    private String generateCSVStringHeader(Map<String, String> preparedObjectInfo) {
        StringBuilder sb = new StringBuilder();

        // Produces the header and stores it to a String
        for (Map.Entry<String, String> entry : preparedObjectInfo.entrySet()) {
            if(entry.getKey().startsWith("get")) {
                sb.append(entry.getKey().substring(3));
            } else if (entry.getKey().startsWith("is")) {
                sb.append(entry.getKey().substring(2));
            }
            sb.append(DELIMITER);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        return sb.toString();
    }

    private Map<String, String> generateClassMethodsAndData(Class clazz, Object obj)
            throws ClassNotFoundException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {

        if (clazz == null)
            throw new ClassNotFoundException("No class is provided");
        if (obj == null)
            throw new IllegalArgumentException("Object cannot be null");

        Map<String, String> classData = new HashMap<>();

        // Using reflection in order to mirror the classes methods and it's objects values
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 0 && method.getName().startsWith("get") || method.getName().startsWith("is")) {
                if (method.invoke(obj) != null) {
                    classData.put(method.getName(), method.invoke(obj).toString());
                } else {
                    classData.put(method.getName(), "N/A");
                }
            }
        }
        return classData;
    }

    // Helper method to access all parent classes
    private boolean hasParent(Class clazz) {
        return clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class;
    }

    // Helper method to sort information map with a template provided from the object's class
    private Map<String, String> templateSort(Map<String, String> objectinfo, String[] sortingTemplate) {
        LinkedHashMap<String, String> sortedData = new LinkedHashMap<>();
        for (int i = 0; i < sortingTemplate.length; i++) {
            for (Map.Entry<String, String> entry : objectinfo.entrySet()) {
                if (sortingTemplate[i].equals(entry.getKey())) {
                    sortedData.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return sortedData;
    }

    // To access the object's template(s)
    private String[] findTemplate(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getDeclaredMethods();
        String[] template = null;

        for (Method method : methods) {
            if (method.getName().startsWith("template")) {
                template = (String[]) method.invoke(obj);
            }
        }
        return template;
    }
}

