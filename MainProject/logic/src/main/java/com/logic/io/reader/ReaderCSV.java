package com.logic.io.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderCSV {


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

