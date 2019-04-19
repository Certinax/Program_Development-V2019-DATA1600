package com.logic.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>String Validator</h1>
 *
 * Class with static methods to valide Strings in the application
 *
 * @author Fredrik Pedersen
 * @since 15-04-2019
 */

public class DataValidator {

    private DataValidator() { //Private constructor to deter initialization
    }

    public static boolean addressChecker(String address) {
        String[] adrSplit = address.split(" ");

        if (adrSplit.length != 2) {
            return false;
        }

        if (adrSplit[0].length() < 2) {
            return false;
        }

        if (!isInteger(adrSplit[1])) {
            return false;
        }
        return true;
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean intLengthChecker(int input, int desiredLength) {
        int length = (int) (Math.log10(input) + 1);

        return length == desiredLength;
    }

    public static boolean emailChecker(String email) {
        //This regex mathces any string that has two words consisting of any characters and numbers, separated by an @.
        //There is also a third set of characters which is separated from the original pair by a punctuation mark.
        Pattern regex = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");
        Matcher matcher = regex.matcher(email);

        return matcher.find();
    }
}
