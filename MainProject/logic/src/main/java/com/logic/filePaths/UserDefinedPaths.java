package com.logic.filePaths;

import com.logic.utilities.exceptions.UnsupportedFileFormatException;

/**
 * <h1>UserDefinedPaths</h1>
 *
 * This class set paths defined by user.
 *
 * @author Fredrik Pedersen
 * @since 20-04-2019
 */

public class UserDefinedPaths {

    private static String substituteCSVPath;
    private static String substituteJOBJPath;

    private static String employerCSVPath;
    private static String employerJOBJPath;

    private static String availablePositionCSVPath;
    private static String availablePositionJOBJPath;

    public static void setSubstituteCSVPath(String path) throws UnsupportedFileFormatException {
        if (path == null) {
            substituteCSVPath = null;
            return;
        }

        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            substituteCSVPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .csv-file!");
        }
    }

    public static void setSubstituteJOBJPath(String path) throws UnsupportedFileFormatException {
        if (path == null) {
            substituteJOBJPath = null;
            return;
        }

        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("jobj")) {
            substituteJOBJPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .jobj-file!");
        }
    }

    public static void setEmployerCSVPath(String path) throws UnsupportedFileFormatException {
        if (path == null) {
            employerCSVPath = null;
            return;
        }

        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            employerCSVPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .csv-file!");
        }
    }

    public static void setEmployerJOBJPath(String path) throws UnsupportedFileFormatException {
        if (path == null) {
            employerJOBJPath = null;
            return;
        }

        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("jobj")) {
            employerJOBJPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .jobj-file!");
        }
    }

    public static void setAvailablePositionCSVPath(String path) throws UnsupportedFileFormatException {
        if (path == null) {
            availablePositionCSVPath = null;
            return;
        }

        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("csv")) {
            availablePositionCSVPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .csv-file!");
        }
    }

    public static void setAvailablePositionJOBJPath(String path) throws UnsupportedFileFormatException {
        if (path == null) {
            availablePositionJOBJPath = null;
            return;
        }

        String[] separator = path.split("\\.");
        if (separator[separator.length-1].equals("jobj")) {
            availablePositionJOBJPath = path;
        } else {
            throw new UnsupportedFileFormatException("You need to select a .jobj-file!");
        }
    }

    public static void setDefaultPaths() {
        try {
            setSubstituteCSVPath(null);
            setSubstituteJOBJPath(null);
            setEmployerCSVPath(null);
            setEmployerJOBJPath(null);
            setAvailablePositionCSVPath(null);
            setAvailablePositionJOBJPath(null);
        } catch (UnsupportedFileFormatException e) {
            // No need to do anything...
        }
    }

    protected static String getSubstituteCSVPath() {
        return substituteCSVPath;
    }

    protected static String getSubstituteJOBJPath() {
        return substituteJOBJPath;
    }

    protected static String getEmployerCSVPath() {
        return employerCSVPath;
    }

    protected static String getEmployerJOBJPath() {
        return employerJOBJPath;
    }

    protected static String getAvailablePositionCSVPath() {
        return availablePositionCSVPath;
    }

    protected static String getAvailablePositionJOBJPath() {
        return availablePositionJOBJPath;
    }
}
