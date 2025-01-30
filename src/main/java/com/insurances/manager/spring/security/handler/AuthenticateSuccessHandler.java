package com.insurances.manager.spring.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurances.manager.controller.dto.UserDTO;
import com.insurances.manager.spring.security.jwt.JwtEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticateSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private JwtEncoder	jwtEncoder;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpStatus.OK.value());
		
		PrintWriter writer = response.getWriter();

		try {
			final UserDetails detail = (UserDetails) authentication.getPrincipal();
			final String token = jwtEncoder.generate(detail);
			Map<String, Object> payload = new HashMap<>();
			payload.put("token",token);
			
			Map<String, Object> user = new HashMap<String, Object>();
			user.put("id", ((UserDTO) detail).getId());
			user.put("username", detail.getUsername());
			user.put("authorities", detail.getAuthorities());
			payload.put("user", user);
			
			writer.append(mapper.writeValueAsString(payload));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			
		}
		writer.close();
	}

}
