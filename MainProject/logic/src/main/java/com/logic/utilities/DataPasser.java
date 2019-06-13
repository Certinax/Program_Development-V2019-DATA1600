package com.logic.utilities;

/**
 * <h1>DataPasser</h1>
 *
 * Class for passing data between other classes, utilizing a static object
 *
 * @author Fredrik Pedersen
 * @since 01-05-2019
 */

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
