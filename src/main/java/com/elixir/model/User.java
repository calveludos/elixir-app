package com.elixir.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class User implements Serializable {
    private int id;
    private String email;
    private String username;
    private String password;
    private String verificationCode;
    private Timestamp registrationDate;
    private boolean isVerified;

    public User() {
        this.verificationCode = UUID.randomUUID().toString().substring(8);
        this.registrationDate = new Timestamp(System.currentTimeMillis());
    }

    public User(boolean bool) {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.verificationCode = UUID.randomUUID().toString().substring(8);
        this.registrationDate = new Timestamp(System.currentTimeMillis());
        this.isVerified = false;
    }

    public User(String email, String username, String password, String verificationCode, Timestamp registrationDate, boolean isVerified) {
        this.email = email;
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", registrationDate=" + registrationDate +
                ", isVerified=" + isVerified +
                '}';
    }

}
