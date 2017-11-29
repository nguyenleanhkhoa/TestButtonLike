package com.example.anhkhoa.testbuttonlike;

/**
 * Created by anh khoa on 11/18/2017.
 */

public class User {
    public String tenuser;
    public String iduser;
    public String email;
    public String pass;

    public User() {
    }

    public User(String tenuser, String iduser, String email, String pass) {
        this.tenuser = tenuser;
        this.iduser = iduser;
        this.email = email;
        this.pass = pass;
    }

    public String getTenuser() {
        return tenuser;
    }

    public void setTenuser(String tenuser) {
        this.tenuser = tenuser;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
