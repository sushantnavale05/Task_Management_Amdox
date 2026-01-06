package com.TaskManagement1.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	private final Key key;
	private final long expirationTokenMS=1000L* 60 *60*24;
	
	public JWTUtil() {
		String secret = System.getenv("JWT_SECRET");
		if(secret == null ||secret.isEmpty()) {
			
			secret= "ReplaceThisWithAVerySecretKey";
		}
		key=Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String subject) {
		
		Date now = new Date();
		Date expiry = new Date(now.getTime()+expirationTokenMS);
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(now)
				.setExpiration(expiry)
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean validateToken(String token) {
		
		try {
			
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
			
		}
	}
	
	public String getSubject(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

}
