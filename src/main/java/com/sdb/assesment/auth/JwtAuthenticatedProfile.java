package com.sdb.assesment.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.sdb.assesment.model.User;

@SuppressWarnings("serial")
@Component
public class JwtAuthenticatedProfile implements Authentication {
	
	@Autowired
	private User user;
	
	
	public JwtAuthenticatedProfile(Map<String, Object> hashMap) {
		for (Map.Entry<String, Object> item : hashMap.entrySet()) {
			if (item.getKey().equals("user")) {
				User user = (User) item.getValue();
				this.user = user;
			}
		}
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
}
