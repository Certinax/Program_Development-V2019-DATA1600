package com.logic.io.parser;

import com.logic.utilities.exceptions.CSVParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * <h1>CSVParser</h1>
 *
 * This class parses a .csv-file and provides a list of lists, where first list will be
 * the classname of the objects that are stored to the file, second will be header of file corresponding to
 * the fields of the class and it's superclasses provided. Each line following will represent an own list in the list
 * as an object.
 *
 * @author Candidate 511
 * @4since 15-04-2019
 *
 */
public class CSVParser {

    public CSVParser() {}

    public List<List<String>> getInfo(String path) throws CSVParseException {

        List<List<String>> fileInfo = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;

            String firstLine = reader.readLine();
            String[] classInfo = firstLine.split(";");
            fileInfo.add(Arrays.asList(classInfo));

            String secondLine = reader.readLine();

            if(firstLine.isEmpty()) {
                throw new CSVParseException("The document is empty");
            }

            String[] header = secondLine.split(";");
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
                    throw new CSVParseException("CSV-file is corrupt. Headerline and infoline doesn't match. " +
                            "This might be caused of wrong usage of delimiter and/or other conflicting symbols");
                }
            }
            return fileInfo;

        } catch (IOException e) {
            throw new CSVParseException();
        }
    }
}
