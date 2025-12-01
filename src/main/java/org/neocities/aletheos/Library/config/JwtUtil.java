package org.neocities.aletheos.Library.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	private final Key signingKey;
	private final long validForMilliseconds = 60_000L;

	public JwtUtil() {
		String secret = "whyOnEarthWouldYouDoThisWhenLearningSecurity1";
		this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String username, String role) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
			.setSubject(username)
			.claim("role", role)
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + validForMilliseconds))
			.signWith(signingKey, SignatureAlgorithm.HS256)
			.compact()
		;
	}

	public Jws<Claims> parseClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(signingKey)
			.build()
			.parseClaimsJws(token)
		;
	}
}
