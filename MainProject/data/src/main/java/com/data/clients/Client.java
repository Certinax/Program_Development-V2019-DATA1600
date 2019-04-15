package com.data.clients;

import com.data.clients.infoclasses.Personalia;

public abstract class Client {

    private Personalia personalia;

    public Client(Personalia personalia) {

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