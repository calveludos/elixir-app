package com.elixir.model;

import java.sql.Date;

public class User {
    
    private long id;
    private String email;
    private String user_name;
    private String name;
    private String password;
    private String code_verify;
    private Date date_register;
    private boolean is_verify;


    public long getId() {
        return id;
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

    public Date getDate_register() {
        return date_register;
    }

    public boolean getIs_verify() {
        return is_verify;
    }

    public void setId(long id) {
        this.id = id;
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
        this.password = password;
    }

    public void setCode_verify(String code_verify) {
        this.code_verify = code_verify;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
    }

    public void setIs_verify(boolean is_verify) {
        this.is_verify = is_verify;
    }
}
