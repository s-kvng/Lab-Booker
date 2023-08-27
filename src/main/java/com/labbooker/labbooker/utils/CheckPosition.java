package com.labbooker.labbooker.utils;

public class CheckPosition {


    //this allows us to always create a single instance of the class
    private static final CheckPosition instance = new CheckPosition();

    private String position;

    private String email;

    private CheckPosition(){};

    public static CheckPosition getInstance(){
        return instance;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
