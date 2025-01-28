package com.insurances.manager.spring.security.jwt;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.insurances.manager.spring.security.utils.AuthUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtEncoder {

	@Value("${jwt.key}")
	private String key;

	public String generate(UserDetails details) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		AuthUtils authUtils = AuthUtils.getInstance();
		String[] authorities = authUtils.AuthoritiesToClaims(details.getAuthorities());

		DateTime 	startAt = DateTime.now().withTimeAtStartOfDay();
		DateTime 	expireAt = startAt.plusHours(23).plusMinutes(59).plusSeconds(59).plusMillis(999);

		Encoder		enconder = Base64.getEncoder();
	
		return Jwts.builder()
			.header()
				.type("JWT")
			.and()
			.subject(details.getUsername())
			.issuedAt(startAt.toDate())
			.expiration(expireAt.toDate())
			.claim("authorities", authorities)
			.signWith(SignatureAlgorithm.HS256, enconder.encodeToString(key.getBytes()))
			.compact();
	}

}
