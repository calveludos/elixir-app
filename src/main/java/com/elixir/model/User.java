package com.elixir.model;

import java.sql.Timestamp;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class User implements  Model{

    private String email;
    private String user_name;
    private String name;
    private String password;
    private String code_verify = UUID.randomUUID().toString();
    private Timestamp date_register = new Timestamp(System.currentTimeMillis());
    private boolean is_verify = false;

    public User() {
    }

    public User(String email, String user_name, String name, String password) {
        setEmail(email);
        setUser_name(user_name);
        setName(name);
        setPassword(password);
    }
    public User(String email, String user_name, String name, String password, String code_verify, Timestamp date_register, boolean is_verify) {
        setEmail(email);
        setUser_name(user_name);
        setName(name);
        setPassword(password);
        setCode_verify(code_verify);
        setDate_register(date_register);
        setIs_verify(is_verify);
    }

    public String getEmail() {
        return email;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCode_verify() {
        return code_verify;
    }

    public Timestamp getDate_register() {
        return date_register;
    }

    public boolean getIs_verify() {
        return is_verify;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setCode_verify(String code_verify) {
        this.code_verify = code_verify;
    }

    public void setDate_register(Timestamp date_register) {
        this.date_register = date_register;
    }

    public void setIs_verify(boolean is_verify) {
        this.is_verify = is_verify;
    }
}
