package com.insurances.manager.services.impl;


import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurances.manager.domain.entity.ClientEntity;
import com.insurances.manager.domain.repository.ClientRepository;
import com.insurances.manager.services.ClientService;
import com.insurances.manager.services.UserService;
import com.insurances.manager.services.mapper.ClientMapper;
import com.insurances.manager.services.model.Authority;
import com.insurances.manager.services.model.Client;
import com.insurances.manager.services.model.User;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private UserService service;
	
	private ClientMapper mapper = ClientMapper.INSTANCE;

	@Override
	public Collection<Client> fetchAll() {
		return mapper.map(repository.findAll());
	}

	@Override
	public Client fetchById(Long id) {
		return mapper.map(repository.getReferenceById(id));
	}

	@Override
	public Client create(Client client) {
		ClientEntity record = null;

		if(Objects.isNull(client.getUser())) {
			User user = User.builder()
					.authorities(Collections.singleton(Authority.builder()
							.name("client")
							.build()))
					.username(client.getEmail()).build();
			
			user = service.create(user);
			client.setUser(user);
			
			record = mapper.map(client);
		} else {
			User user = client.getUser();
			if(Objects.nonNull(user.getUsername())) {
				Optional.ofNullable(service.fetchById(user.getId())).ifPresent(stored -> {
					stored.setUsername(user.getUsername());					
					client.setUser(service.create(stored));
				});
			}
			
			record = mapper.map(client);
		};
		
		
		return mapper.map(repository.save(record));
	}

	@Override
	public Client delete(Long id) {
		Client client = mapper.map(repository.findById(id).get());
		repository.deleteById(id);
		service.delete(client.getUser().getId());
		
		return client;
	}

}
