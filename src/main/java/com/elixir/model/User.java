package com.elixir.model;

import java.sql.Timestamp;
import java.util.UUID;

public class User {
    private int id;
    private String email;
    private String userName;
    private String name;
    private String password;
    private String verificationCode;
    private Timestamp registrationDate;
    private boolean isVerified;

    public User() {
        this.verificationCode = UUID.randomUUID().toString();
        this.registrationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(String email, String userName, String name, String password) {
        this.email = email;
        this.userName = userName;
        this.name = name;
        setPassword(password);
        this.verificationCode = UUID.randomUUID().toString();
        this.registrationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(String email, String userName, String name, String password, String verificationCode, Timestamp registrationDate, boolean isVerified) {
        this.email = email;
        this.userName = userName;
        this.name = name;
        setPassword(password);
        this.verificationCode = verificationCode;
        this.registrationDate = registrationDate;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {}

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
