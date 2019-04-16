package com.data.clients.infoclasses;

import java.util.UUID;

/**
 * <h1>Clientinfo</h1>
 *
 * This dataclass is used for abstract class Client
 *
 * Using Java's UUID class to generate a client identification number. This method
 * of creating ID is Threadsafe.
 *
 * @Author Mathias Lund Ahrn
 * @Since 16-04-19
 */
public class Clientinfo {

    // Required information
    private Email email;
    private Phonenumber phonenumber;
    private UUID client_ID;


    public Clientinfo(Email email, Phonenumber phonenumber) {
        this.email = email;
        this.phonenumber = phonenumber;
        client_ID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Clientinfo{" +
                "email=" + email +
                ", phonenumber=" + phonenumber +
                ", clientID=" + client_ID +
                '}';
    }

    public UUID getClient_ID(){
        return client_ID;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phonenumber getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Phonenumber phonenumber) {
        this.phonenumber = phonenumber;
    }
}
