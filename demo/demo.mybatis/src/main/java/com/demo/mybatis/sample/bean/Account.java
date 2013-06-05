package com.demo.mybatis.sample.bean;

import java.util.Date;

public class Account {

    private int id;

    private String name;

    private String password;

    private Role role;

    private Date created;

    private Date lastLoginTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id:").append(id);
        builder.append("\nname:").append(name);
        builder.append("\npassword:").append(password);
        builder.append("\nrole:").append(role);
        builder.append("\ncreated:").append(created);
        builder.append("\nlastLoginTime:").append(lastLoginTime);

        return builder.toString();
    }
}
