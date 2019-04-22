package com.data;

import com.data.client.Client;
import com.data.client.Employee;
import com.data.client.Employer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DataTesting {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {

        ArrayList<String> joblist = new ArrayList<>();
        joblist.add("Consultant");
        Employer emp = new Employer("Certinax", "Fjellstadvegen 31", 2009, "Nordby", "Private", "IT", joblist);

        Class clazz = emp.getClass();
        Class parent = clazz.getSuperclass();
        Method[] methods = clazz.getDeclaredMethods();
        //printMethod(methods, emp);
        /*while(parent != null && parent != Object.class) {
            methods = parent.getDeclaredMethods();
            parent = parent.getSuperclass();
            printMethod(methods, emp);
        }*/

        System.out.println("Format til class.toString() "+ clazz.getName());
        Method[] methodsx = Client.class.getDeclaredMethods();

        String[] clientTemplate =  {"getName", "getAddress", "getZipcode", "getCity"};

        //ArrayList<Method> templated = new ArrayList<>();
        Method[] templated = new Method[clientTemplate.length];

        /*for(Method m : methodsx) {
            if(m.getName().startsWith("get")) {
                if(m.getName().equals(clientTemplate[0])) {
                    System.out.println("Funkz");
                    templated.add(m);
                }
            }
        }*/

       /* for (int i = 0; i < clientTemplate.length; i++) {
            for(Method m : methodsx) {
                if(m.getName().startsWith("get") && m.getName().equals(clientTemplate[i])) {
                    templated[i] = m;
                }
            }
        }*/

        System.out.println(emp.getClass().getTypeName());

        /*for (Method m:
             templated) {
            System.out.println(m);
        }*/
        /*
        ArrayList<String> education = new ArrayList<>();
        ArrayList<String> jobExperience = new ArrayList<>();
        ArrayList<String> jobRequirements = new ArrayList<>();
        ArrayList<String> references = new ArrayList<>();

        String edc1 = "Kristelig Gymnas, 2007-2010, college";
        education.add(edc1);

        String jobXp1 = "Ica Maxi Strømmen, 2007-2012";
        jobExperience.add(jobXp1);

        String jobReq1 = "Frontend development";
        jobRequirements.add(jobReq1);

        String ref1 = "Karen Hannasvik, email, telefon";
        references.add(ref1);

        Employee sub = new Employee("Mathias", "Ahrn", "Fjellstadvegen 31",
                2009, "Nordby", 9999, 28, 25, education,jobExperience,jobRequirements,references);

        clazz = sub.getClass();
        parent = clazz.getSuperclass();

        methods = clazz.getDeclaredMethods();
        printMethod(methods);

        while(parent != null && parent != Object.class) {
            methods = parent.getDeclaredMethods();
            parent = parent.getSuperclass();
            printMethod(methods);
        }*/

    }

    private static void printMethod(Method[] methods, Employer obj) throws IllegalAccessException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        for(Method m : methods) {
            if(m.getName().startsWith("get")) {
                System.out.println(m.getName() + "value: " +m.getDefaultValue());
                if(m.invoke(obj) != null) {
                    //System.out.println("DETTE BLIR IKKE NULLPOINTER");
                    System.out.println("Dette skal printes ut som null");
                }
            }
        }
    }
}
