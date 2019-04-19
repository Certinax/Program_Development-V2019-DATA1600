package com.data.io.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderCSV {

    public static List<List<String>> readClient(String path) throws IOException {
        ArrayList<List<String>> clients = new ArrayList<List<String>>();
        BufferedReader reader = null;

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

}

