package com.sdb.assesment.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdb.assesment.auth.LoginCredentials;
import com.sdb.assesment.dto.UserResponse;
import com.sdb.assesment.enums.ResponseEnum;
import com.sdb.assesment.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private String controllerName = "LOGIN CONTROLLER - ";
	
	@PostMapping("/user")
	public UserResponse officerLogin(ServletRequest request, @RequestBody LoginCredentials loginCredentials, HttpServletResponse resp) {
		UserResponse response = new UserResponse();
		try {
			response = loginService.userLogin(request, loginCredentials, resp);
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
		}
		return response;
	}
}
