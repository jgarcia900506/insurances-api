package com.insurances.manager.spring.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurances.manager.controller.dto.UserDTO;
import com.insurances.manager.domain.repository.UserRepository;
import com.insurances.manager.spring.security.mapper.UserMapper;
import com.insurances.manager.spring.security.service.AuthenticationService;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository repository;

	private UserMapper mapper = UserMapper.INSTANCE;

	@Override
	@Transactional
	public UserDTO authenticate(String username, String password) {
		return repository.findByUsername(username).map(mapper::map).orElse(null);
	}

}
