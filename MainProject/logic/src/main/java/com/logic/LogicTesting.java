package com.logic;

import com.logic.validators.accountValidator;

public class LogicTesting {

    public static void main(String[] args) {

        boolean check = accountValidator.emailChecker("fred3443rikhp@fa123.cfggdom");
        System.out.println(check);
    }
}
