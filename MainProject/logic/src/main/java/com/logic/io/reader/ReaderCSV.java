package com.logic.io.reader;

import com.logic.io.parser.CSVParser;
import com.logic.utilities.exceptions.CSVParseException;
import com.logic.utilities.exceptions.SerializationException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * <h1>ReaderCSV</h1>
 *
 * This class is used for reading .csv-files and returning an array list of objects.
 * It builds object based on using reflection.
 *
 * In order for this class to work, a private default constructor must be provided.
 *
 * Datatypes this reader supports:
 *  - Primtive datatypes
 *  - String
 *  - ArrayList
 *
 * @author Candidate 530
 * @since 12-04-2019
 */
public class ReaderCSV implements Reader {

    public ReaderCSV() {}


    @Override
    public <T> ArrayList<T> readObjects(String path) throws CSVParseException, SerializationException {
        CSVParser parser = new CSVParser();

        List<List<String>> fileInfo = parser.getInfo(path);
        ArrayList<T> objects;
        try {
            Class clazz = Class.forName(fileInfo.get(0).get(1));
            objects = generateObject(clazz, fileInfo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SerializationException();
        }
        return objects;
    }

    @SuppressWarnings("unchecked")
    private <T> ArrayList<T> generateObject(Class clazz, List<List<String>> info) throws SerializationException{

        try {
            Constructor<?> defaultConstructor = getDefaultConstructor(clazz);

            if(defaultConstructor == null) {
                throw new SerializationException("The class provided is not supported");
            }

            defaultConstructor.setAccessible(true);

            T instance = (T)defaultConstructor.newInstance();

            // Getting all fields from instance
            Field[] fields = getFields(instance);

            // Getting the header list
            List<String> header = info.get(1);

            if(!listComparing(header, fields)) {
                throw new SerializationException("Header and field does not correspond");
            }

            // Creating a new lists with only objects
            List<List<String>> objects = new ArrayList<>();
            for (int i = 2; i < info.size(); i++) {
                objects.add(info.get(i));
            }


            LinkedHashMap<String, Field> boilerPlate = new LinkedHashMap<>();
            for (String headerItem : header) {
                for (int i = 0; i < fields.length; i++) {
                    if (headerItem.equals(fields[i].getName())) {
                        boilerPlate.put(headerItem, fields[i]);
                        break;
                    }
                }
            }

            ArrayList<T> objectList = new ArrayList<>();
            int column = 0;
            for (int i = 0; i < objects.size(); i++) {
                instance = (T)defaultConstructor.newInstance();
                for (Map.Entry<String, Field> entry : boilerPlate.entrySet()) {
                    if(entry.getValue().getType().isPrimitive()) {
                        entry.getValue().setAccessible(true);
                        setPrimitive(objects.get(i).get(column), instance, entry.getValue());
                    } else if (entry.getValue().getType().equals(String.class)) {
                        entry.getValue().setAccessible(true);
                        setString(objects.get(i).get(column), instance, entry.getValue());
                    } else {
                        entry.getValue().setAccessible(true);
                        setList(objects.get(i).get(column), instance, entry.getValue());
                    }
                    column++;
                    if (column == boilerPlate.size()) {
                        column = 0;
                    }
                }
                objectList.add(instance);
            }

            return objectList;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException  e) {
            throw new SerializationException();
        }
    }

    private boolean listComparing(List<String> list, Field[] fields) {
        if(list.size() == fields.length) {
            boolean equal = true;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < fields.length; j++) {
                    if(list.get(i).equals(fields[j].getName())) {
                        equal = true;
                        break;
                    } else {
                        equal = false;
                    }
                }
            }
            return equal;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Field[] getFields (T instance) {

        ArrayList<T> fieldsList = new ArrayList<>();

        Class clazz = instance.getClass();

        while(clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fieldsList.add((T)fields[i]);
            }
            clazz = clazz.getSuperclass();
        }

        // Converting ArrayList to an Array of Field
        Field[] fieldOfAllClasses = new Field[fieldsList.size()];
        for (int i = 0; i < fieldOfAllClasses.length; i++) {
            fieldOfAllClasses[i] = (Field)fieldsList.get(i);
        }

        return fieldOfAllClasses;
    }

    private Constructor<?> getDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors) {
            if(constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }


    private <T> void setList(String info, T instance, Field field) throws IllegalAccessException {

        String type = field.getType().getName();
        String[] infoList = info.split(",");
        String[] formattedInfoList = new String[infoList.length];

        for (int i = 0; i < infoList.length; i++) {
            formattedInfoList[i] = infoList[i].trim();
        }

        List<String> list = new ArrayList<>(Arrays.asList(formattedInfoList));
        if(type.equals(ArrayList.class.getName())) {
            field.set(instance, list);
        }
    }

    private <T> void setString(String info, T instance, Field field) throws IllegalAccessException {
        String type = field.getType().getName();
        if(type.equals(String.class.getName())) {
            field.set(instance, info);
        }
    }

    private <T> void setPrimitive(String info, T instance, Field field) throws IllegalAccessException, NumberFormatException{
        String type = field.getType().getName();

        if (type.equals(int.class.getName())) {
            field.setInt(instance, Integer.parseInt(info));
        } else if(type.equals(boolean.class.getName())) {
            field.setBoolean(instance, Boolean.parseBoolean(info));
        } else if (type.equals(short.class.getName())) {
            field.setShort(instance, Short.parseShort(info));
        } else if (type.equals(byte.class.getName())) {
            field.setByte(instance, Byte.parseByte(info));
        } else if (type.equals(char.class.getName())) {
            field.setChar(instance, info.length() == 0 ? ' ' : info.charAt(0));
        } else if (type.equals(float.class.getName())) {
            field.setFloat(instance, Float.parseFloat(info));
        } else if (type.equals(double.class.getName())) {
            field.setDouble(instance, Double.parseDouble(info));
        } else if (type.equals(long.class.getName())) {
            field.setLong(instance, Long.parseLong(info));
        }
    }

}

