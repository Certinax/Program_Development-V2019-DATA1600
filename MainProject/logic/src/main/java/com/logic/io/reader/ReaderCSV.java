package com.logic.io.reader;

import com.logic.io.parser.CSVParser;
import com.logic.utilities.exceptions.CSVParseException;
import com.logic.utilities.exceptions.SerializationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

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

    public <T> T read(String path, Class clazz) throws IOException, CSVParseException {
        CSVParser parser = new CSVParser();
        //System.out.println(parser.getInfo(path).size());

        List<List<String>> fileInfo = parser.getInfo(path);
        System.out.println("Header: " + fileInfo.get(0));

        generateObject(clazz, fileInfo);

        return null;

    }

    @SuppressWarnings("unchecked")
    private <T> T generateObject(Class clazz, List<List<String>> info) {

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

            System.out.println(listComparing(header, fields));


            for (int i=1; i < info.size(); i++) {
                List<String> obj = info.get(i);
                for (int j = 0; j < obj.size(); j++) {
                    System.out.println(header.get(j) + ": " + obj.get(j));
                    // TODO ER FRA HER Objektene skal bygges med setPrimitive, setList, setString

                }
            }

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

    private <T> void setList(String info, T instance, Field field) throws IllegalAccessException {
        String type = field.getType().getName();
        String[] infoList = info.split(",");
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(infoList));
        if(type.equals(ArrayList.class.getName())) {
            field.set(instance, list);
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


    public static List<List<String>> readObject(String path, Class clazz) throws IOException {
        ArrayList<List<String>> clients = new ArrayList<List<String>>();
        BufferedReader reader = null;

        Constructor[] constructor = clazz.getDeclaredConstructors();
        for (Constructor c : constructor) {
            System.out.println(Arrays.toString(c.getParameterTypes()));
        }


        try {
            reader = new BufferedReader(new FileReader(path));
            String line;

            while((line=reader.readLine()) != null) {
                String[] values = line.split(";");
                clients.add(Arrays.asList(values));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
        return clients;
    }

    private static List<Object> parseObject(ArrayList<List<String>> objects, Class clazz) {
        ArrayList<Object>  objList = new ArrayList<>();


        return objList;
    }
}

