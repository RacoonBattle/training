package com.demo.mybatis.sample.bean;

public enum Role {

	USER(0, "普通用户"), MANAGER(1, "管理员");

	private int code;
	private String text;

	private Role(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int code() {
		return code;
	}

	public String text() {
		return text;
	}

	public static Role codeOf(int code) {
		for (Role role : values()) {
			if (role.code == code) {
				return role;
			}
		}

		throw new IllegalArgumentException("Invalid role code: " + code);
	}
}
