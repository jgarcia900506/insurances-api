package com.insurances.manager.spring.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurances.manager.domain.entity.UserEntity;
import com.insurances.manager.domain.repository.UserRepository;
import com.insurances.manager.services.mapper.UserMapper;
import com.insurances.manager.services.model.User;
import com.insurances.manager.spring.security.service.AuthenticationService;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	private UserMapper mapper = UserMapper.INSTANCE;

	@Override
	@Transactional
	public User authenticate(String username, String password) {
		UserEntity user = repository.findByUsername(username).get();
		
		if(!encoder.matches(password, user.getPassword())) {
			throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
		}
		
		return mapper.map(user);
	}

}
