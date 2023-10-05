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
    private String codeVerify;
    private Timestamp dataRegister;
    private boolean isVerify;

    public User() {
        this.codeVerify = UUID.randomUUID().toString().substring(0, 8);
        this.dataRegister = new Timestamp(System.currentTimeMillis());
        this.isVerify = false;
    }

    public User(String email, String username, String password, String codeVerify, Timestamp dataRegister, boolean isVerify) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.codeVerify = codeVerify;
        this.dataRegister = dataRegister;
        this.isVerify = isVerify;
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

    public String getCodeVerify() {
        return codeVerify;
    }

    public void setCodeVerify(String codeVerify) {
        this.codeVerify = codeVerify;
    }

    public Timestamp getDataRegister() {
        return dataRegister;
    }

    public void setDataRegister(Timestamp dataRegister) {
        this.dataRegister = dataRegister;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public void setHashPassword(String password){
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", codeVerify='" + codeVerify + '\'' +
                ", dataRegister=" + dataRegister +
                ", isVerify=" + isVerify +
                '}';
    }
}
