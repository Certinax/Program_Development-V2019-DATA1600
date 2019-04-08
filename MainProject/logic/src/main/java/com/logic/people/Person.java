package com.logic.people;

import com.logic.people.infoclasses.Personalia;

public abstract class Person {

    private Personalia personalia;

    public Person(Personalia personalia) {

        this.personalia = personalia;
    }

    public Personalia getPersonalia() {
        return personalia;
    }

    public void setPersonalia(Personalia personalia) {
        this.personalia = personalia;
    }

    public String toString() {
        return personalia.toString();
    }
}