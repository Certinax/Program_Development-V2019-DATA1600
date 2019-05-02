package com.logic.utilities;

public class DataPasser {

    private static Object data;

    private DataPasser() {} //Private constructor to deter initialization

    public static void setData(Object data) {
        DataPasser.data = data;
    }

    public static Object getData() {
        return data;
    }
}
