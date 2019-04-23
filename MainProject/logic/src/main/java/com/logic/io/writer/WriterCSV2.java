package com.logic.io.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WriterCSV2 {

    public static void writeObject(Object obj, String path, boolean append, Object... sortingTemplate) throws Exception, IOException {
        FileWriter filewriter = null;

        File file = new File(path);

        System.out.println(file.length());

        boolean header = false;
        if(file.length() == 0) {
            header = true;
        }

        String objInStringFormat = generateCSVInfo(obj, header, sortingTemplate);

        filewriter = new FileWriter(path, append);
        filewriter.write(objInStringFormat);
        filewriter.flush();
        filewriter.close();

    }

    private static String generateCSVInfo(Object obj, boolean header, Object... sortingTemplate) throws IllegalAccessException,
            InvocationTargetException, ClassNotFoundException{

        Objects.requireNonNull(obj);
        Class clazz = obj.getClass();
        Map<String, String> objectInfo = new HashMap<>();

        objectInfo.putAll(generateClassMethodsAndData(clazz, obj));
        while (hasParent(clazz)) {
            clazz = clazz.getSuperclass();
            objectInfo.putAll(generateClassMethodsAndData(clazz, obj));
        }

        Map<String, String> preparedObjectInfo;
        StringBuilder csvInfo = new StringBuilder();
        // Ideen er nå at jeg har et map med alle metoder og verdiene deres
        // Neste steg er å sortere keysettet etter sortingTemplate
        if(header) {
            if (sortingTemplate != null) {
                preparedObjectInfo = templateSort(objectInfo, sortingTemplate);
                csvInfo.append(generateCSVStringHeader(preparedObjectInfo));
                csvInfo.append(generateCSVStringData(preparedObjectInfo));
                csvInfo.append("\n");
                System.out.println(csvInfo.toString());
            } else {
                csvInfo.append(generateCSVStringData(objectInfo));
            }
        } else if(!header) {
            if(sortingTemplate != null) {
                preparedObjectInfo = templateSort(objectInfo,sortingTemplate);
                csvInfo.append(generateCSVStringData(preparedObjectInfo));
                csvInfo.append("\n");
            } else {
                csvInfo.append(generateCSVStringData(objectInfo));
            }
        }
        csvInfo.deleteCharAt(csvInfo.length()-1);
        return csvInfo.toString();
    }

    private static String generateCSVStringData(Map<String, String> preparedObjectInfo) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String,String> entry : preparedObjectInfo.entrySet()) {
            sb.append(entry.getValue());
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        return sb.toString();
    }

    private static String generateCSVStringHeader(Map<String, String> preparedObjectInfo) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String,String> entry : preparedObjectInfo.entrySet()) {
            sb.append(entry.getKey().substring(3));
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        return sb.toString();
    }

    private static boolean hasParent(Class clazz) {
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            return true;
        }
        return false;
    }

    private static Map<String, String> generateClassMethodsAndData(Class clazz, Object obj)
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
                }
            }
        }
        return classData;
    }

    private static Map<String, String> templateSort(Map<String, String> objectinfo, Object... sortingTemplate) {
        LinkedHashMap<String, String> sortedData = new LinkedHashMap<>();
        for(int i = 0; i < sortingTemplate.length; i++) {
            for (Map.Entry<String,String> entry : objectinfo.entrySet()) {
                if(sortingTemplate[i].equals(entry.getKey())) {
                    sortedData.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return sortedData;
    }

}
