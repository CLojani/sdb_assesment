package com.sdb.assesment.service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdb.assesment.auth.LoginCredentials;
import com.sdb.assesment.dto.UserResponse;
import com.sdb.assesment.enums.ResponseEnum;
import com.sdb.assesment.model.User;
import com.sdb.assesment.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;

	public UserResponse userLogin(ServletRequest request, LoginCredentials loginCredentials, HttpServletResponse resp) {
		UserResponse response = new UserResponse();
		try {
			User user = userRepository.findByUsername(loginCredentials.getUsername());
			if (user == null) {
				response.setCode(ResponseEnum.USERNAME_NOT_FOUND.getCode());
				response.setMessage(ResponseEnum.USERNAME_NOT_FOUND.getMessage());
			} else {
				PasswordService ps = new PasswordService();
				if(ps.decrypt(user.getPassword()).equals(loginCredentials.getPassword())) {
					if (user.getStatus().equals("A")) {
						String accessToken = jwtService.tokenFor(user.getUsername(), "user");
						resp.setHeader("token", accessToken);
						
						response.setToken(accessToken);
						response.setUserId(user.getUserId());
						response.setCode(ResponseEnum.SUCCESS.getCode());
						response.setMessage(ResponseEnum.SUCCESS.getMessage());
					} else {
						response.setCode(ResponseEnum.INACTIVE_USER.getCode());
						response.setMessage(ResponseEnum.INACTIVE_USER.getMessage());
					}
				} else {
					response.setCode(ResponseEnum.INCORRECT_PASSWORD.getCode());
					response.setMessage(ResponseEnum.INCORRECT_PASSWORD.getMessage());
				}
			}
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(e.getLocalizedMessage());
		}
		return response;
	}

}
