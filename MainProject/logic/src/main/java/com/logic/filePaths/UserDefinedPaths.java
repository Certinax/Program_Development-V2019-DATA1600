package com.logic.filePaths;

import com.logic.utilities.exceptions.UnsupportedFileFormatException;

public class UserDefinedPaths {

    private static String substituteCSVPath;
    private static String substituteJOBJPath;

    private static String employerCSVPath;
    private static String employerJOBJPath;

    private static String availablePositionCSVPath;
    private static String availablePositionJOBJPath;

    public static void setSubstituteCSVPath(String path) throws UnsupportedFileFormatException {
        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            substituteCSVPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .csv-file!");
        }
    }

    public static void setSubstituteJOBJPath(String path) throws UnsupportedFileFormatException {
        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("jobj")) {
            substituteJOBJPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .jobj-file!");
        }
    }

    public static void setEmployerCSVPath(String path) throws UnsupportedFileFormatException {
        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            employerCSVPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .csv-file!");
        }
    }

    public static void setEmployerJOBJPath(String path) throws UnsupportedFileFormatException {
        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            employerJOBJPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .jobj-file!");
        }
    }

    public static void setAvailablePositionCSVPath(String path) throws UnsupportedFileFormatException {
        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            availablePositionCSVPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .csv-file!");
        }
    }

    public static void setAvailablePositionJOBJPath(String path) throws UnsupportedFileFormatException {
        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            availablePositionJOBJPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .jobj-file!");
        }
    }

    public static String getSubstituteCSVPath() {
        return substituteCSVPath;
    }

    public static String getSubstituteJOBJPath() {
        return substituteJOBJPath;
    }

    public static String getEmployerCSVPath() {
        return employerCSVPath;
    }

    public static String getEmployerJOBJPath() {
        return employerJOBJPath;
    }

    public static String getAvailablePositionCSVPath() {
        return availablePositionCSVPath;
    }

    public static String getAvailablePositionJOBJPath() {
        return availablePositionJOBJPath;
    }
}
