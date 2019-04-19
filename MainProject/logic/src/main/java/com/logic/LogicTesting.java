package com.logic;

import com.logic.validators.ListValidator;

import java.util.ArrayList;

public class LogicTesting{

    public static void main(String[] args) {

        PersonTesting person = new PersonTesting("Ola", "Nordmann");
        //PersonTesting person2 = null;

        ArrayList<PersonTesting> list1 = new ArrayList<>();
        list1.add(person);
        //list1.add(person2);

        ListValidator.requireNonNull(list1);
    }
}
