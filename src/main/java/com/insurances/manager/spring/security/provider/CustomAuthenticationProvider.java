package com.insurances.manager.spring.security.provider;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.insurances.manager.controller.mapper.UserDTOMapper;
import com.insurances.manager.services.model.User;
import com.insurances.manager.spring.security.service.AuthenticationService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AuthenticationService service;

	private UserDTOMapper mapper = UserDTOMapper.INSTANCE;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());

		User user = service.authenticate(username, password);

		if(Objects.isNull(user)) {
			throw new UsernameNotFoundException("User not found with Credential provided!");
		}

		return new UsernamePasswordAuthenticationToken(mapper.map(user), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
