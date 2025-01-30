package com.insurances.manager.services.impl;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurances.manager.domain.entity.UserEntity;
import com.insurances.manager.domain.repository.UserRepository;
import com.insurances.manager.services.AuthorityService;
import com.insurances.manager.services.UserService;
import com.insurances.manager.services.mapper.UserMapper;
import com.insurances.manager.services.model.Authority;
import com.insurances.manager.services.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthorityService service;

	private UserMapper mapper = UserMapper.INSTANCE;

	@Override
	public User create(User user) {

		user.getAuthorities().stream().filter(authority -> Objects.isNull(authority.getId())).forEach(authority -> {
			Authority record = service.fetchByName(authority.getName());
			authority.setId(record.getId());
		});

		UserEntity record = mapper.map(user);
		record.setPassword(String.valueOf(UUID.randomUUID()));
		

		return mapper.map(repository.save(record));
	}

	@Override
	public User fetchById(Long id) {
		return repository.findById(id).map(mapper::map).orElse(null);
	}

	@Override
	public User delete(Long id) {
		User user = mapper.map(repository.findById(id).get());
		repository.deleteById(id);
		
		return user;
	}

}
