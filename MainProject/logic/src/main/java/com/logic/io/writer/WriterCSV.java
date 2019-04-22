package com.logic.io.writer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class WriterCSV {

    private WriterCSV() { //Private constructor to deter initialization
    }

    public static boolean writeObject(Object object, String path, boolean append, String[]... sortingTemplate) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path, append);
            fileWriter.write(createCSVInfo(object, sortingTemplate));
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

    private static String writingCSVInfo(Class clazz, Object obj, String[]... sortingTemplate) throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {

        if(clazz == null) throw new ClassNotFoundException("The class doesn't exist!");

        StringBuilder sb = new StringBuilder();

        Method[] methods = clazz.getDeclaredMethods();
        /*Method[] template;
        if(isSortable(clazz)) {
            methods = templateSort(MethodTemplateSorter.template(obj), clazz.getDeclaredMethods());
        }*/


        // TODO
        //  DENNE BLOKKEN SKAL I EGEN METODE
        /*Class[] interfaces = obj.getClass().getInterfaces();
        for(Class c : interfaces) {
            System.out.println(c.toString());
            if(c.getName().equals("com.data.client.TemplateSort")) {
                System.out.println("Nå kan klassen sorteres");
                MethodTemplateSorter.template(obj);
            }
        }*/

        String[] template = null;
        if(obj.getClass().getName().equals("com.data.client.Employee")) {
            template = employeeTemplate(clazz);
        } else if(obj.getClass().getName().equals("com.data.client.Employer")) {
            template = employerTemplate(clazz);
        }

        // Array to keep track of all methods of the client
        //Method[] methods = clazz.getDeclaredMethods();

        methods = templateSort(template, clazz.getDeclaredMethods());



        // TODO This block is for debugging purposes, delete upon delivery
        /*if(clazz.toString().equals("class com.data.client.Client")) {
            for (Method m : methods) {
                    System.out.println(m.toString());
            }
        }*/



        // Writing headers for the following data
        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                if (m.getName().startsWith("get")) {
                    if (m.invoke(obj) != null) {
                        sb.append(m.getName().substring(3)).append(",");
                    }
                } else if (m.getName().startsWith("is")) {
                    sb.append(m.getName().substring(2)).append(",");
                }
            }
        }


        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");

        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    if (m.invoke(obj) != null) {
                        System.out.println(m.invoke(obj).toString());
                        sb.append(m.invoke(obj).toString());
                        sb.append(",");
                    }
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }

    private static String[] employerTemplate(Class clazz) {
        String[] template;
        switch (clazz.getName()) {
            case "com.data.client.Employer":
                template =  new String[] {"getSector", "getIndustry", "getJoblist"};
                break;
            case "com.data.client.Client":
                template = new String[] {"getName", "getAddress", "getZipcode", "getCity"};
                break;
            default:
                return null;
        }
        return template;
    }

    private static String[] employeeTemplate(Class clazz) {
        String[] template;
        switch(clazz.getName()) {
            case "com.data.client.Employee":
                template = new String[] {"getSsn", "getAge", "getSalaryRequirement",
                        "getEducation", "getJobExperience", "getJobRequirements", "getReferences"};
                break;
            case "com.data.client.Client":
                template = new String[] {"getFirstname", "getLastname", "getAddress", "getZipcode", "getCity"};
                break;
            default:
                return null;
        }
        return template;
    }

    private static Method[] templateSort(String[] template, Method[] methods) {
        Method[] templatedMethods = new Method[template.length];

        for (int i = 0; i < template.length; i++) {
            for(Method m : methods) {
                if(m.getName().startsWith("get") && m.getName().equals(template[i])) {
                    templatedMethods[i] = m;
                }
            }
        }
        return templatedMethods;
    }

    private static boolean isSortable(Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        for(Class c : interfaces) {
            System.out.println(c.toString());
            if(c.getName().equals("com.data.client.TemplateSort")) {
                System.out.println("Nå kan klassen sorteres");
                return true;
            }
        }
        return false;
    }

    private static boolean isEmpty(Method m) {
        return m.getName() != null;
    }

    private static String createCSVInfo(Object obj, String[]... sortingTemplate) throws IllegalArgumentException, IllegalAccessException,
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
