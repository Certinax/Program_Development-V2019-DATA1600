package com.logic.filePaths;

public class ActivePaths {

    public static String getSubstituteCSVPath() {
        if (UserDefinedPaths.getSubstituteCSVPath() != null) {
            return  UserDefinedPaths.getSubstituteCSVPath();
        } else {
            return DefaultFilePaths.SUBSTITUTESCSV.toString();
        }
    }

    public static String getSubstituteJOBJPath() {
        if (UserDefinedPaths.getSubstituteJOBJPath() != null) {
            return  UserDefinedPaths.getSubstituteJOBJPath();
        } else {
            return DefaultFilePaths.SUBSTITUTESJOBJ.toString();
        }
    }

    public static String getEmployerCSVPath() {
        if (UserDefinedPaths.getEmployerCSVPath() != null) {
            return  UserDefinedPaths.getEmployerCSVPath();
        } else {
            return DefaultFilePaths.EMPLOYERCSV.toString();
        }
    }

    public static String getEmployerJOBJPath() {
        if (UserDefinedPaths.getEmployerJOBJPath() != null) {
            return  UserDefinedPaths.getEmployerJOBJPath();
        } else {
            return DefaultFilePaths.EMPLOYERJOBJ.toString();
        }
    }

    public static String getAvailablePositionCSVPath() {
        if (UserDefinedPaths.getAvailablePositionCSVPath() != null) {
            return  UserDefinedPaths.getAvailablePositionCSVPath();
        } else {
            return DefaultFilePaths.AVAILABLEPOSITIONCSV.toString();
        }
    }

    public static String getAvailablePositionJOBJPath() {
        if (UserDefinedPaths.getAvailablePositionJOBJPath() != null) {
            return  UserDefinedPaths.getAvailablePositionJOBJPath();
        } else {
            return DefaultFilePaths.AVAILABLEPOSITIONJOBJ.toString();
        }
    }

    public static String getActivePositionCSVPath() {
        if (UserDefinedPaths.getActivePositionCSVPath() != null) {
            return  UserDefinedPaths.getActivePositionCSVPath();
        } else {
            return DefaultFilePaths.ACTIVEPOSITIONCSV.toString();
        }
    }

    public static String getActivePositionJOBJPath() {
        if (UserDefinedPaths.getActivePositionJOBJPath() != null) {
            return  UserDefinedPaths.getActivePositionJOBJPath();
        } else {
            return DefaultFilePaths.ACTIVEPOSITIONJOBJ.toString();
        }
    }
}
