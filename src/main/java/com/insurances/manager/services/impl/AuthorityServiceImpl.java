package com.insurances.manager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insurances.manager.domain.entity.AuthorityEntity;
import com.insurances.manager.domain.repository.AuthorityRepository;
import com.insurances.manager.services.AuthorityService;
import com.insurances.manager.services.model.Authority;
import com.insurances.manager.spring.security.mapper.AuthorityMapper;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	private AuthorityMapper mapper = AuthorityMapper.INSTANCE;

	@Override
	public Authority fetchByName(String name) {
		
		AuthorityEntity record = AuthorityEntity.builder()
									.name(name).build();
		Example<AuthorityEntity> example = Example.of(record);
		repository.findOne(example).ifPresent(entity -> {
			record.setId(entity.getId());
		});
		
		return mapper.map(record);
	}

}
