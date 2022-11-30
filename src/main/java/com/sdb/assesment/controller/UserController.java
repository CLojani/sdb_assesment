package com.sdb.assesment.controller;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdb.assesment.dto.UserResponse;
import com.sdb.assesment.enums.LoggerMessageEnum;
import com.sdb.assesment.enums.ResponseEnum;
import com.sdb.assesment.model.User;
import com.sdb.assesment.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private String controllerName = "USER CONTROLLER - ";
	
	@GetMapping("/all")
	private UserResponse getAll(ServletRequest request) {
		UserResponse response = new UserResponse();
		try {
			response = userService.getAll(request);
			logger.info(controllerName + LoggerMessageEnum.CREATE.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
			logger.info(controllerName + LoggerMessageEnum.CREATE.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue());
		}
		return response;
	}

	@PostMapping
	private UserResponse create(ServletRequest request, @RequestBody User user) {
		UserResponse response = new UserResponse();
		try {
			response = userService.create(request, user);
			logger.info(controllerName + LoggerMessageEnum.CREATE.getValue() + LoggerMessageEnum.METHOD_EXECUTED.getValue());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
			logger.info(controllerName + LoggerMessageEnum.CREATE.getValue() + LoggerMessageEnum.ERROR_OCCURED.getValue());
		}
		return response;
	}
	
}
