package com.sdb.assesment.dto;

import java.util.List;

public class UserResponse {

	private int code;
	private String message;
	private String token;
	private long userId;
	private List<UserDTO> userList;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<UserDTO> getUserList() {
		return userList;
	}
	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}
	
	
}
