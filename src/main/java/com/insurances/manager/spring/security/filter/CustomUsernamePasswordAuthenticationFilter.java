package com.insurances.manager.spring.security.filter;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurances.manager.controller.dto.CredentialsDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private ObjectMapper jsonMapper = null;
	
	public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager manager, AuthenticationSuccessHandler handler) {
		super(new AntPathRequestMatcher("/login", "POST"));
		setAuthenticationManager(manager);
		
		JsonFactory jsonFactory = new JsonFactory();
		jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		
		jsonMapper = new ObjectMapper(jsonFactory);
		setAuthenticationSuccessHandler(handler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		CredentialsDTO credentials = null;
		
		try {
			credentials = jsonMapper.readValue(request.getInputStream(), CredentialsDTO.class);
		}catch (IOException e) {
			credentials = new CredentialsDTO(null, null);
			logger.warn(":: Credentials not found!");
		}

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword(), Collections.emptyList()));
	}	

}
