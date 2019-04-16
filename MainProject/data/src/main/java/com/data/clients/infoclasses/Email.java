package com.data.clients.infoclasses;

import com.logic.exceptions.InvalidMailException;
import com.logic.validators.DataValidator;

/**
 * <h1>Email</h1>
 *
 * Class for making email objects
 *
 * @author Fredrik Pedersen
 * @since 04-04-2019
 */

public class Email {

    private String email;


    /**
     * Constructor used to create an email object
     *
     * @param email - the mailadress you want to make an object of
     * @throws InvalidMailException if the mailaddress is incorectly formated
     */
    public Email(String email) throws InvalidMailException {

        if (!DataValidator.emailChecker(email)) {
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

    @Override
    public String toString() {
        return "Email: " + email;
    }
}
