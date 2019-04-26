package com.logic.io.parser;

import com.logic.utilities.exceptions.CSVParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVParser {

    public CSVParser() {};


    public List<List<String>> getInfo(String path) throws CSVParseException {

        List<List<String>> fileInfo = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            String firstLine = reader.readLine();

            if(firstLine.isEmpty()) {
                throw new CSVParseException();
            }

            String[] header = firstLine.split(";");
            for (int i = 0; i < header.length; i++) {
                char[] c = header[i].toCharArray();
                c[0] = Character.toLowerCase(c[0]);
                header[i] = String.copyValueOf(c);
            }
            fileInfo.add(Arrays.asList(header));


            while((line = reader.readLine()) != null) {
                line = line.trim();

                String[] info = line.split(";");
                if(info.length == header.length) {
                    fileInfo.add(Arrays.asList(info));
                } else {
                    throw new CSVParseException("CSV-file is corrupt. Headerline and infoline doesn't match");
                }
            }
            return fileInfo;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
