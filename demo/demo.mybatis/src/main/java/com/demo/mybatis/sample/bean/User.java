package com.demo.mybatis.sample.bean;

import java.util.Date;

public class User {

	private int id;

	private String name;

	private Role role;

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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id);
		builder.append("\nname:").append(name);
		builder.append("\nrole:").append(role);
		builder.append("\nlastLoginTime:").append(lastLoginTime);

		return builder.toString();
	}
}
