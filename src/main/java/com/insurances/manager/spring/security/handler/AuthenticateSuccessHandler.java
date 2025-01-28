package com.insurances.manager.spring.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.insurances.manager.spring.security.jwt.JwtEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticateSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private JwtEncoder	jwtEncoder;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpStatus.OK.value());
		
		PrintWriter writer = response.getWriter();

		try {
			writer.append(jwtEncoder.generate((UserDetails) authentication.getPrincipal()));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			
		}
		writer.close();
	}

}
