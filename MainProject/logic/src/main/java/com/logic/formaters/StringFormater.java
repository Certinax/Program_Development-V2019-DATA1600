package com.logic.formaters;

public class StringFormater {

    public static String formatName(String name) {
        char[] nameSplit = name.toCharArray();

        nameSplit[0] = Character.toUpperCase(nameSplit[0]);

        for (int i = 1; i < nameSplit.length; i++) {
            nameSplit[i] = Character.toLowerCase(nameSplit[i]);
        }

        return new String(nameSplit);
    }

    public static String formatUsername(String username) {
        char[] usernameSplit = username.toCharArray();

        for (int i = 0; i < usernameSplit.length; i++) {
            usernameSplit[i] = Character.toLowerCase(usernameSplit[i]);
        }

        return new String(usernameSplit);
    }
}
