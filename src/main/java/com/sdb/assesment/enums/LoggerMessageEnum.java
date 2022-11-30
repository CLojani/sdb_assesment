package com.sdb.assesment.enums;

public enum LoggerMessageEnum {

	METHOD_EXECUTED(" METHOD EXECUTED"),
	ERROR_OCCURED(" ERROR OCCURED"),
	CREATE_USER("CREATE USER"),
	CREATE("CREATE"),
	USER_LOGIN("USER LOGIN"),
	GET_USERS("GET USERS");
	
	private String value;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private LoggerMessageEnum(String value) {
		this.value = value;
	}
	
}
