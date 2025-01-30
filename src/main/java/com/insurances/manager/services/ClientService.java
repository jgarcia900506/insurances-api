package com.insurances.manager.services;

import java.util.Collection;

import com.insurances.manager.services.model.Client;

public interface ClientService {

	Collection<Client> fetchAll();
	Client fetchById(Long id);
	Client create(Client client);
	Client delete(Long id);

}
