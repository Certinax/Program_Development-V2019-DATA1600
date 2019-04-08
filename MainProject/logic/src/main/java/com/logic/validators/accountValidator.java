package com.logic.validators;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class accountValidator {

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
        /* String[] mailcheckat = email.split("@");
        String[] mailcheckdot = email.split("");

        System.out.println(Arrays.toString(mailcheckdot));

        System.out.println(mailcheckat.length == 2);
        System.out.println(mailcheckdot.length == 2);

        return mailcheckat.length == 2 && mailcheckdot.length == 2; */

        Pattern regex = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]\\.[a-z]{2,6}$");
        Matcher matcher = regex.matcher(email);

        System.out.println(matcher.find());
        return matcher.find();
    }
}
