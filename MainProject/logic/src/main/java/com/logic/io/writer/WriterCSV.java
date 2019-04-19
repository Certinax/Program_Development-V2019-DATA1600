package com.logic.io.writer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class WriterCSV {

    private WriterCSV() { //Private constructor to deter initialization
    }

    public static boolean writeObject(Object object, String path, boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path, append);
            fileWriter.write(createCSVInfo(object));
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private static String writingCSVInfo(Class clazz, Object obj) throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {

        if(clazz == null) throw new ClassNotFoundException("The class doesn't exist!");

        StringBuilder sb = new StringBuilder();


        // Array to keep track of all methods of the client
        Method[] methods = clazz.getDeclaredMethods();

        // Writing headers for the following data
        for(Method m : methods) {
            if(m.getParameterTypes().length == 0) {
                if(m.getName().startsWith("get")) {
                    sb.append(m.getName().substring(3)).append(",");
                } else if(m.getName().startsWith("is")) {
                    sb.append(m.getName().substring(2)).append(",");
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");

        for(Method m : methods) {
            if(m.getParameterTypes().length == 0) {
                if(m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    System.out.println(m.invoke(obj).toString());
                    sb.append(m.invoke(obj).toString());
                    sb.append(",");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }

    private static String createCSVInfo(Object obj) throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        if(obj == null) {
            throw new IllegalArgumentException("No object to write");
        }


        StringBuilder sb = new StringBuilder();

        ArrayList<String> structure = new ArrayList<String>();


        String[] sa = null;

        try {
            Class<?> clazz = obj.getClass();

            //sb.append(writingCSVInfo(clazz,obj));

            // Følgende utkommentert kode er under utvikling

            sa = writingCSVInfo(clazz, obj).split("\n");
            structure.addAll(Arrays.asList(sa));


            // Making sure to get all parent classes
            Class parent = clazz.getSuperclass();
            while (parent != null && parent != Object.class) {
                sa = writingCSVInfo(parent, obj).split("\n");
                structure.addAll(Arrays.asList(sa));
                parent = parent.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO sortertingsløkkene nedenfor skal i egne private metoder

        // This code sort the data from an object so all the titles comes in order based on
        // the class hierarchy (Class A, B. B extends A. A params before B params)
        for(int i = structure.size()-1; i >= 0; i--) {
            if(i%2 == 0) {
                sb.append(structure.get(i));
                sb.append(",");
            }
            if(i == 0) {
                sb.deleteCharAt(sb.length()-1);
            }
        }
        sb.append("\n");

        // This code sort the data from an object so all the information comes in order
        // based on the class hierarchy
        for(int i = structure.size()-1; i >= 0; i--) {
            if(i%2 != 0) {
                sb.append(structure.get(i));
                sb.append(",");
            }
            if(i == 0) {
                sb.deleteCharAt(sb.length()-1);
            }
        }
        sb.append("\n");

        return sb.toString();
    }


    private static String[] trimStringEnd(String[] sa) {
        String[] saNew = new String[sa.length];
        for(int i = 0; i < sa.length; i++) {
            saNew[i] = sa[i].substring(sa[i].length()).replace(",","");
        }
        for(String s : saNew) {
            System.out.println(s);
        }
        return saNew;
    }


}
