package com.labbooker.labbooker;

import com.labbooker.labbooker.models.GetLecturerData;

public class LoginData {

    private static final LoginData instance = new LoginData();

    private String name;

    private String index_no;

    private String email;

    private String password;

    private String className;

    private int reset_password;


    public LoginData(){};

//    public LoginData(String email, String password, int reset_password) {
//        this.email = email;
//        this.password = password;
//        this.reset_password = reset_password;
//    }

    public static LoginData getInstance(){
        return instance;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex_no() {
        return index_no;
    }

    public void setIndex_no(String index_no) {
        this.index_no = index_no;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
