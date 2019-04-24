package com.gui;

import com.data.client.Job;
import com.data.client.SortingTemplates;
import com.data.client.Substitute;
import com.logic.io.reader.ReaderCSV;
import com.logic.io.writer.WriterCSV2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class CombinedTesting {

    public static void main(String[] args) {

        ArrayList<String> job = new ArrayList<>();
        job.add("Job");
        ArrayList<String> education = new ArrayList<>();
        education.add("Education");
        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("JobXp");
        ArrayList<String> ref = new ArrayList<>();
        ref.add("References");

        Substitute sub1 = new Substitute("Ole", "Kristiansen", "Hovligata 12", 1121, "Oslo", 1232123, 22, 30, education,
                jobxp, job, ref);

        // WriterCSV.writeObject(sub1, "employees.csv", true);

        /*
         * try { WriterCSV2.writeObject(sub1, "test.csv", true,
         * SortingTemplates.substituteTemplate()); } catch (Exception e) {
         * e.printStackTrace(); }
         */

        Class clazz = com.data.client.Substitute.class;

        Job job1 = new Job("IT consultant");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().startsWith("get")) {
                System.out.println(m.getReturnType());

            }
        }

        /*
         * try { clazz = Class.forName("com.data.client.Job"); Constructor<?>
         * defaultConstructor = getDefaultConstructor(clazz);
         * 
         * 
         * if(defaultConstructor == null) { throw new
         * ClassNotFoundException("Unsupported class"); }
         * 
         * } catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
         */

        try {
            // WriterCSV2.writeObject(job1, "job.csv", true,
            // SortingTemplates.jobTemplate());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // System.out.println(ReaderCSV.readObject("job.csv", clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Method[] methods = sub1.getClass().getDeclaredMethods();
        // System.out.println(methods[1].getName());
    }

    private static Constructor<?> getDefaultConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }
}
