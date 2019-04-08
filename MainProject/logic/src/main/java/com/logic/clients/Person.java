package com.logic.clients;

import com.logic.clients.infoclasses.Personalia;

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

    @Override
    public String toString() {
        return personalia.toString();
    }
}