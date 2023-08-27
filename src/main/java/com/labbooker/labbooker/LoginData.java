package com.labbooker.labbooker;

public class LoginData {

    private String email;

    private String password;

    private int reset_password;


    public LoginData(){};

    public LoginData(String email, String password, int reset_password) {
        this.email = email;
        this.password = password;
        this.reset_password = reset_password;
    }

    public int getReset_password() {
        return reset_password;
    }

    public void setReset_password(int reset_password) {
        this.reset_password = reset_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
