package com.logic.accounts;

import com.logic.accounts.accountinfo.LoginCredentials;
import com.logic.accounts.accountinfo.Personalia;

public abstract class UserAccount {

    private Personalia personalia;
    private LoginCredentials credentials;

    public UserAccount(Personalia personalia, LoginCredentials credentials) {

        this.personalia = personalia;
        this.credentials = credentials;
    }

    public Personalia getPersonalia() {
        return personalia;
    }

    public void setPersonalia(Personalia personalia) {
        this.personalia = personalia;
    }

    public LoginCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(LoginCredentials credentials) {
        this.credentials = credentials;
    }

    public String toString() {
        return personalia.toString();
    }
}