package com.logic.accounts.accountinfo;

import com.logic.exceptions.InvalidMailException;
import com.logic.validators.accountValidator;

public class Email {

    private String email;

    public Email(String email) throws InvalidMailException {

        if (accountValidator.emailChecker(email)) {
            throw new InvalidMailException("An email must be given on the form \"address@provider.domain\"");
        }

        this.email = email;
    }

    public String getMailadress() {
        return email;
    }

    public void setMailadress(String email) {
        this.email = email;
    }

    public String toString() {
        return "Email: " + email;
    }
}
