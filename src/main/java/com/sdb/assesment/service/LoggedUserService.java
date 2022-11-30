package com.sdb.assesment.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Service;

import com.sdb.assesment.model.User;

@Service
public class LoggedUserService {

	public long getLoggedUserId(ServletRequest request){
		Map<String, Object> loggedUserMap = (Map<String, Object>)request.getAttribute("user");
		long loggedUserId = 0;
		for (Map.Entry<String, Object> entry : loggedUserMap.entrySet()) {  
			
			if (entry.getKey().equals("user")) {
				User user = (User) entry.getValue();
				loggedUserId = user.getUserId();
			}
		}
		return loggedUserId;
	}

}
