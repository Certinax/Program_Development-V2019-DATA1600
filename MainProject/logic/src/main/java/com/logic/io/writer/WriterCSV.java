package com.logic.io.writer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class writes objects to csv file.
 * It can take an template input as an string array for sorting the data in desired order
 *
 * @Author Mathias Lund Ahrn, Fredrik Pedersen
 * @since 18-04-2019
 */

public class WriterCSV implements Writer {

    public void writeObject(Object obj, String path) throws IllegalAccessException,
            InvocationTargetException, ClassNotFoundException, IOException {
        ObservableList<Object> object = FXCollections.observableArrayList();
        object.add(obj);

        writeObjects(object, path);
    }

    public <T> void writeObjects(ObservableList<T> objects, String path)
            throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, IOException {


        FileWriter filewriter = null;
        File file = new File(path);
        ArrayList<String> infoToWrite = new ArrayList<>();
        boolean header = true;

        for (Object object : objects) {
            infoToWrite.add(generateCSVInfo(object, header));
            if (header) {
                header = false;
            }
        }

        try {
            filewriter = new FileWriter(file);
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
        Class clazz = obj.getClass();
        Map<String, String> objectInfo = new HashMap<>();
        String[] sortingTemplate = findTemplate(obj);


        // TODO This can be handled in a seperate method
        boolean sorting = false;
        if (sortingTemplate.length > 0) {
            sorting = true;
        }

        // TODO Check for parameterized constructor call
        objectInfo.putAll(generateClassMethodsAndData(clazz, obj));
        while (hasParent(clazz)) {
            clazz = clazz.getSuperclass();
            objectInfo.putAll(generateClassMethodsAndData(clazz, obj));
        }

        Map<String, String> preparedObjectInfo;
        StringBuilder csvInfo = new StringBuilder();
        // Ideen er nå at jeg har et map med alle metoder og verdiene deres
        // Neste steg er å sortere keysettet etter sortingTemplate
        if (header) {
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
        } else if (!header) { // TODO Evaluate this later
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

        for (Map.Entry<String, String> entry : preparedObjectInfo.entrySet()) {
            sb.append(entry.getValue());
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        return sb.toString();
    }

    private String generateCSVStringHeader(Map<String, String> preparedObjectInfo) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : preparedObjectInfo.entrySet()) {
            sb.append(entry.getKey().substring(3));
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        return sb.toString();
    }

    private boolean hasParent(Class clazz) {
        return clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class;
    }

    private Map<String, String> generateClassMethodsAndData(Class clazz, Object obj)
            throws ClassNotFoundException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {

        if (clazz == null)
            throw new ClassNotFoundException("No class is provided");
        if (obj == null)
            throw new IllegalArgumentException("Object cannot be null");

        Map<String, String> classData = new HashMap<>();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 0 && method.getName().startsWith("get")) {
                if (method.invoke(obj) != null) {
                    classData.put(method.getName(), method.invoke(obj).toString());
                } else {
                    classData.put(method.getName(), "");
                }
            }
        }
        return classData;
    }

    private Map<String, String> templateSort(Map<String, String> objectinfo, String[] sortingTemplate) {
        LinkedHashMap<String, String> sortedData = new LinkedHashMap<>();
        for (int i = 0; i < sortingTemplate.length; i++) { // TODO Evaluate if a foreach loop is more convenient
            for (Map.Entry<String, String> entry : objectinfo.entrySet()) {
                if (sortingTemplate[i].equals(entry.getKey())) {
                    sortedData.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return sortedData;
    }

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

