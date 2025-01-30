package com.insurances.manager.spring.security.jwt;

import java.io.UnsupportedEncodingException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.insurances.manager.controller.dto.AuthorityDTO;
import com.insurances.manager.controller.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtDecoder {

	@Value("${jwt.key}")
	private String key;

	@SuppressWarnings("unchecked")
	public UserDTO validate(String token)throws JwtException, IllegalArgumentException, UnsupportedEncodingException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		Encoder		enconder = Base64.getEncoder();
		Claims		claims	= Jwts.parser()
								.setSigningKey(key.getBytes())
								.build()
								.parseClaimsJws(token)
								.getBody();
		
		ArrayList<String>	raw		= claims.get("authorities", ArrayList.class);
		Set<AuthorityDTO>	roles	= raw.stream().map(new Function<String, AuthorityDTO>() {

			@Override
			public AuthorityDTO apply(String role) {
				return AuthorityDTO.builder().name(role).build();
			}
			
		}).collect(Collectors.toSet());
		
		return UserDTO.builder().username(claims.getSubject()).authorities(roles).build();
	}

}
