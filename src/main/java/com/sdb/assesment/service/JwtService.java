package com.sdb.assesment.service;

import static java.time.ZoneOffset.UTC;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdb.assesment.model.User;
import com.sdb.assesment.repository.UserRepository;
import com.sdb.assesment.util.ObjectCastUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService{
    private static final String ISSUER = "sdb";
    public static final String USERNAME = "sdb@";
    public static final String encodedKey = "!sdb@sdb@2022";
    public static final int expirationHours = 8;
    
    private final User user;
    public User getUser() {
		return user;
	}

	public JwtService(User user) {
		this.user = user;
	}

	@Autowired(required = true)
    UserRepository userRepository;
	
	public String tokenFor(String username, String userType) throws IOException, URISyntaxException {
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(expirationHours).toInstant(UTC));
        return Jwts.builder()
                .setId(userType)
                .setSubject(username)
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, encodedKey.getBytes())
                .compact();
    }
    
    public Map<String, Object> verify(String token) throws IOException, URISyntaxException{
    	Object obj = new Object();
    	Map<String, Object> userMap = new HashMap<String, Object>(); 
    	
    	Jws<Claims> claims = Jwts.parser().setSigningKey(encodedKey.getBytes()).parseClaimsJws(token);
    	String username = claims.getBody().getSubject().toString();
    	String userType = claims.getBody().getId().toString();
    	
    	if (userType.equals("user")) {
        	User user = userRepository.findByUsername(username);
        	obj =  ObjectCastUtil.userObjToObject(user);
        	userMap.put("user", obj);
		}
    	return userMap;
    }
    
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(encodedKey.getBytes()).parseClaimsJws(token).getBody();
    }
    
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    private Date calculateExpirationDate(Date createdDate) {
        return  Date.from(LocalDateTime.now(UTC).plusHours(expirationHours).toInstant(UTC));
    }
    
    public String refreshToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, encodedKey.getBytes()).compact();
    }
    
    public String expireToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = Date.from(LocalDateTime.now(UTC).toInstant(UTC));

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, encodedKey.getBytes()).compact();
    }

}

