package com.logic.admins;

/**
 * <h1>Administrator</h1>
 *
 * Class used to make an administrator object. Administrators are the users of the program.
 *
 */

public class Administrator {

    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
