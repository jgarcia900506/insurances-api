package com.insurances.manager.services;

import java.util.Collection;

import com.insurances.manager.services.model.Policy;

public interface PolicyService {

	Collection<Policy> fetchAll();
	Policy fetchById(Long id);
	Policy create(Policy payload);

}
