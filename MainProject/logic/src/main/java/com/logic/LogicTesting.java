package com.logic;

import static com.logic.validators.StringValidator.stringLengthIsValid;

public class LogicTesting{

    public static void main(String[] args) {

        String s = "asdasd";

        System.out.println(stringLengthIsValid(s, 10));
    }
}
