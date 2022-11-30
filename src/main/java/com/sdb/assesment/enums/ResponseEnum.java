package com.sdb.assesment.enums;

public enum ResponseEnum {

	SUCCESS(200, "Success !"),
	USERNAME_NOT_FOUND(414, "User not found !"),
	USER_ALREADY_EXITS(416, "User already exits !"),
	ERROR(400, "Error !"),
	INACTIVE_USER(405,"Inactive user"),
	INCORRECT_PASSWORD(406,"Incorrect password");
	
	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private ResponseEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
