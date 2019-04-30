package com.logic.filePaths;

/**
 * <h1>FilePaths</h1>
 *
 * Enum clas representing all the default files for the program
 *
 * @author Fredrik Pedersen
 * @since 28-04-2019
 */

public enum DefaultFilePaths { //TODO Make sure all paths are included here

    SUBSTITUTESCSV { //The CSV-file for substitutes
        public String toString() {
            return "resources/substitutes.csv";
        }
    },

    SUBSTITUTESJOBJ { //The JOBJ-file for substitutes
        public String toString() {
            return "resources/substitutes.jobj";
        }
    },

    AVAILABLEPOSITIONCSV { //The CSV-file for available positions
        public String toString() { return "resources/availablepositions.csv";}
    },

    AVAILABLEPOSITIONJOBJ { //The JOBJ-file for available positions
        public String toString() { return "resources/availablepositions.jobj";}
    },

    EMPLOYERCSV {
        public String toString() { return "resources/employers.csv";}
    },

    EMPLOYERJOBJ {
        public String toString() {
            return "resources/employers.jobj";
        }
    }


}
