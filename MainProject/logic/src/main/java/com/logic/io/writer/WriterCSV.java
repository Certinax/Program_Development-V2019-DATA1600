package com.logic.io.writer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.FileWriter;
import java.util.ArrayList;

public class WriterCSV {

    public static boolean writeClient(Object client) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("clients.csv");
            fileWriter.write(createCSVInfo(client));
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


        String[] sa;

        try {
            Class<?> clazz = obj.getClass();

            sb.append(writingCSVInfo(clazz,obj));

            // Følgende utkommentert kode er under utvikling
            /*
            sa = writingCSVInfo(clazz, obj).split("\n");
            //structure.addAll(Arrays.asList(sa));
            for(String s : sa) {
                structure.add(s);
            }
            System.out.println("Printer ut fra arrayList" + structure.toString());
            */

            // Making sure to get all parent classes
            Class parent = clazz.getSuperclass();
            while (parent != null && parent != Object.class) {
                sb.append(writingCSVInfo(parent, obj));
                parent = parent.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return sb.toString();

        /*
        Class classType = client.getClass();

        StringBuilder sb = new StringBuilder();

        // Array to keep track of all methods of the client
        Method[] methods = classType.getDeclaredMethods();

        // Writing headers for the following data
        for(Method m : methods) {
            if(m.getParameterTypes().length == 0) {
                if(m.getName().startsWith("get")) {
                    sb.append(m.getName().substring(3)).append(";");
                } else if(m.getName().startsWith("is")) {
                    sb.append(m.getName().substring(2)).append(";");
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");

        for(Method m : methods) {
            if(m.getParameterTypes().length == 0) {
                if(m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    System.out.println(m.invoke(client).toString());
                    sb.append(m.invoke(client).toString());
                    sb.append(";");
                }

            }

        }

        sb.deleteCharAt(sb.length()-1);
        return sb.toString();*/

    }


}
