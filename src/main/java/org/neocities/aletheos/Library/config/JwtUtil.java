package org.neocities.aletheos.Library.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
	private final String secret = "whyOnEarthWouldYouDoThisWhenTeachingSecurity1";

	public String generateToken(String username, String role) {
		return Jwts.builder()
			.setSubject(username)
			.claim("role", role)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 60000))
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact()
		;
	}

	public Claims extraClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
}
