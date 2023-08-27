package com.labbooker.labbooker.models;

public class GetLecturerData {

    //this allows us to always create a single instance of the class
    private static final GetLecturerData instance = new GetLecturerData();

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private int reset_password;


    public GetLecturerData(){};

    public static GetLecturerData getInstance(){
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReset_password() {
        return reset_password;
    }

    public void setReset_password(int reset_password) {
        this.reset_password = reset_password;
    }







}
