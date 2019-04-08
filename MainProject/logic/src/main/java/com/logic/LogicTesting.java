package com.logic;

import com.logic.accounts.SubstituteAccount;
import com.logic.accounts.accountinfo.*;
import com.logic.exceptions.InvalidAddressException;
import com.logic.exceptions.InvalidMailException;
import com.logic.exceptions.InvalidPhonenumberException;
import com.logic.exceptions.InvalidPostnumberException;
import com.logic.formaters.StringFormater;
import com.logic.validators.accountValidator;

public class LogicTesting {

    public static void main(String[] args) {

       /* Personalia me = null;
        WorkExperience exp = null;
        WorkExperience exp2 = null; */

      /*  try {
            Address home = new Address("Haugveien 19", 1930);
            Email myMail = new Email("fredrikhp@gmail.com");
            Email bossMail = new Email("test@testesen.no");
            Phonenumber myPhone = new Phonenumber(99504112);
            Phonenumber bossPhone = new Phonenumber(99106201);
            me = new Personalia("Fredrik", "Pedersen", 25, myMail, home, myPhone);
            Reference ref1 = new Reference("Ola", "Potet", bossMail, bossPhone);
            exp = new WorkExperience("Mamma Betaler", "Hushjelp", ref1, 1994, 2019);
            exp2 = new WorkExperience("Pappa Betaler", "Slave", ref1, 1994, 2010);

        } catch (InvalidMailException | InvalidAddressException | InvalidPostnumberException | InvalidPhonenumberException e) {
            e.getMessage();
        }

        LoginCredentials login = new LoginCredentials("fredrik", "hemmelig");
        Education edu = new Education("Software Engineering", "OsloMet", false, true, false, false, false, 2017, 2020);

        SubstituteAccount sub = new SubstituteAccount(me, login, "IT", edu, exp, exp2, 205000 ); */

      Email mail = null;

        accountValidator.emailChecker("fredrikhp@gmail.com");



    }
}
