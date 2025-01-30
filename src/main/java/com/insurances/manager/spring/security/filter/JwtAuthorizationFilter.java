package com.insurances.manager.spring.security.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.insurances.manager.controller.dto.UserDTO;
import com.insurances.manager.spring.security.jwt.JwtDecoder;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtDecoder decoder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
		String       AuthHeader = request.getHeader("Authorization");
		
		if(AuthHeader == null || !AuthHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		
		try {
			UserDTO user = decoder.validate(AuthHeader.substring(7));
			UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (UnrecoverableKeyException | JwtException | IllegalArgumentException | UnsupportedEncodingException
				| KeyStoreException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		chain.doFilter(request, response);
	}

	
}
