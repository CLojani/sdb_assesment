package com.sdb.assesment.auth;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.sdb.assesment.service.JwtService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	private final JwtService jwtService;
	ServletRequest request;

    public JwtAuthenticationProvider() {
        this(null,null);
    }

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService, ServletRequest request) {
    	this.jwtService = jwtService;
        this.request = request;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
        	Map<String, Object> possibleProfile = jwtService.verify((String) authentication.getCredentials());
	        request.setAttribute("user", possibleProfile);
            return new JwtAuthenticatedProfile(possibleProfile);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Failed to verify token", e);
        }
    }
    
    public class JwtAuthenticationException extends AuthenticationException {
        public JwtAuthenticationException(String msg, Throwable t) {
            super(msg, t);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
    
}