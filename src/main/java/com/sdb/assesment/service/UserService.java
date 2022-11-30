package com.sdb.assesment.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdb.assesment.dto.UserDTO;
import com.sdb.assesment.dto.UserResponse;
import com.sdb.assesment.enums.ResponseEnum;
import com.sdb.assesment.model.User;
import com.sdb.assesment.repository.UserRepository;
import com.sdb.assesment.util.DateUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoggedUserService loggedUserService;
	@Autowired
	private PasswordService passwordService;

	public UserResponse getAll(ServletRequest request) {
		UserResponse response = new UserResponse();
		try {
			List<User> users = userRepository.findAll();
			List<UserDTO> userData = users.stream().map(u -> {
				UserDTO user = new UserDTO();
				user.setUserId(u.getUserId());
				user.setDob(DateUtil.dateToString(u.getDob()));
				user.setNic(u.getNic());
				user.setFirstName(u.getFirstName());
				user.setLastName(u.getLastName());
				user.setMobileNo(u.getMobileNo());
				user.setEmail(u.getEmail());
				user.setAddress(u.getAddress());
				user.setUsername(u.getUsername());
				user.setStatus(u.getStatus());
				return user;
			}).collect(Collectors.toList());
			response.setUserList(userData);
			
			response.setCode(ResponseEnum.SUCCESS.getCode());
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	public UserResponse create(ServletRequest request, User user) {
		UserResponse response = new UserResponse();
		try {
			User userObject = userRepository.findByEmail(user.getEmail());
			if (userObject == null) {
				user.setUsername(user.getUsername());
				user.setStatus("A");
				user.setPassword(passwordService.encrypt(user.getPassword()));
				userRepository.save(user);
			}else {
				response.setCode(ResponseEnum.USER_ALREADY_EXITS.getCode());
				response.setMessage(ResponseEnum.USER_ALREADY_EXITS.getMessage());
			}	
			response.setCode(ResponseEnum.SUCCESS.getCode());
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(e.getLocalizedMessage());
		}
		
		return response;
	}
}
