package com.logic.io.reader;

import com.logic.io.parser.CSVParser;
import com.logic.utilities.exceptions.CSVParseException;
import com.logic.utilities.exceptions.SerializationException;
import javafx.util.Builder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is used for reading CSV files. It currently supports data-classes with a public
 * default constructor and fields of primitive types, Strings and Lists.
 *
 * Currently working on support for classes containing builder. (Static nested class)
 *
 * @Author Mathias Lund Ahrn
 * @Since 12-04-2019
 */

public class ReaderCSV {

    public ReaderCSV() {

    };

    private Constructor<?> getDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for(Constructor<?> constructor : constructors) {
            if(constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }

    public <T> ArrayList<T> read(String path, Class clazz) throws CSVParseException {
        CSVParser parser = new CSVParser();

        List<List<String>> fileInfo = parser.getInfo(path);

        return generateObjectsBuilder(clazz, fileInfo);

    }

    @SuppressWarnings("unchecked")
    private <T> ArrayList<T> generateObjectsBuilder(Class clazz, List<List<String>> info) {

        try {

            // Getting the header list
            List<String> header = info.get(0);

            // Creating a new lists with only objects
            List<List<String>> objects = new ArrayList<>();
            for (int i = 1; i < info.size(); i++) {
                objects.add(info.get(i));
            }


            // Generate internal classes to access builder
            System.out.println(Arrays.toString(clazz.getNestMembers()));
            // Getting nested static builder class
            Class[] builder = clazz.getNestMembers();

            // Getting builder constructor
            Constructor[] constructors = builder[1].getConstructors();

            // Getting the required parameters from builder constructor
            Parameter[] requiredParams = constructors[0].getParameters();

            System.out.println(requiredParams[0]);

            // The following part has to go through each object in the object-list
            // and combine header with value. This should be stored in a map.
            for (int i = 0; i < objects.size(); i++) {
                // Create new constructor of current class
                //T instance = (T)constructors[0].newInstance();
                T instance = (T)createInstance(constructors[0], getInfoFromIndex(header, objects.get(i)));
                // Build correct constructor
                // Send in requiredParams[] + getInfoFromIndex and build it

                // This is a linkedHashMap with header and info (<String, String>)
                getInfoFromIndex(header, objects.get(i));
            }



            LinkedHashMap<Parameter, String> requiredFields = new LinkedHashMap<>();

            for (Parameter param : requiredParams) {
                requiredFields.put(param, param.getName());
            }


            //System.out.println(requiredFields.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private <T> T createInstance(Constructor<T> constructor, LinkedHashMap<String, String> info) {
        Object[] paramList = new Object[constructor.getParameterCount()];
        for (Parameter param : constructor.getParameters()) {
            for(Map.Entry<String, String> entry : info.entrySet()) {
                if(param.getName().equals(entry.getKey())) {
                    generateParamObject(param, entry.getValue());
                    break;
                }
            }
        }
        return null;
    }

    private Object generateParamObject(Parameter param, String value) {

        System.out.println(param.getType() + ": " + param.getName() + ": " + value);

        Class<?> classType = param.getType();
        System.out.println(classType);

        try {
            //System.out.println(classType.cast(value));

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;

    }

    private LinkedHashMap<String, String> getInfoFromIndex(List<String> header, List<String> object) {
        LinkedHashMap<String, String> info = new LinkedHashMap<>();
        for (int i = 0; i < header.size(); i++) {
            info.put(header.get(i), object.get(i));
        }
        return info;
    }

    private <T> Constructor<T> buildConstructor(Parameter[] requiredParams, LinkedHashMap<String, String> info) {
        Constructor<T> builderConstructor;
        for (Parameter param : requiredParams) {

        }
        return null;
    }

    private LinkedHashMap<Parameter, String> generateConstructInfo(List<String> info, Parameter[] requiredParams) {
        LinkedHashMap<Parameter, String> requiredFields = new LinkedHashMap<>();

        for (Parameter param : requiredParams) {

        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> ArrayList<T> generateObject(Class clazz, List<List<String>> info) {

        try {
            Constructor<?> defaultConstructor = getDefaultConstructor(clazz);

            if(defaultConstructor == null) {
                throw new SerializationException("The class provided is not supported");
            }

            T instance = (T)defaultConstructor.newInstance();

            // Getting all fields from instance
            Field[] fields = getFields(instance);

            // Getting the header list
            List<String> header = info.get(0);

            // Creating a new lists with only objects
            List<List<String>> objects = new ArrayList<>();
            for (int i = 1; i < info.size(); i++) {
                objects.add(info.get(i));
            }

            // TODO Use this validation (listcompating()) below to do the objectList part later -
            //  do not uncomment the line below
            //System.out.println(listComparing(header, fields));

            LinkedHashMap<String, Field> boilerPlate = new LinkedHashMap<>();
            for (String headerItem : header) {
                for (int i = 0; i < fields.length; i++) {
                    if (headerItem.equals(fields[i].getName())) {
                        boilerPlate.put(headerItem, fields[i]);
                    }
                }
            }


            // TODO logic below should be outsourced to seperate methods
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

        } catch (SerializationException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException  e) {
            e.printStackTrace();
        }
        return null;
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


    // TODO The setField methods below could be outsourced to a seperate class?
    private <T> void setList(String info, T instance, Field field) throws IllegalAccessException {
        String type = field.getType().getName();
        String[] infoList = info.split(",");
        String[] formattedInfoList = new String[infoList.length];

        // TODO This should do
        for (int i = 0; i < infoList.length; i++) {
            formattedInfoList[i] = infoList[i].trim();
        }

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(formattedInfoList));
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

        if(type.equals(boolean.class.getName())) {
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
        } else if (type.equals(int.class.getName())) {
            field.setInt(instance, Integer.parseInt(info));
        } else if (type.equals(long.class.getName())) {
            field.setLong(instance, Long.parseLong(info));
        }
    }

}

