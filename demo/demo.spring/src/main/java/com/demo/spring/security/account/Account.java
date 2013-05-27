package com.demo.spring.security.account;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    private String name;

    private String password;

    private Date created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Override
    public String toString() {
        return String.format("name: %s, password: %s, created: %s", name, password, format.format(created));
    }
}
