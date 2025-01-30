package com.insurances.manager.services;

import java.util.Collection;

import com.insurances.manager.services.model.Policy;

public interface PolicyService {

	Collection<Policy> fetchAll(Long id);
	Policy fetchById(Long id);
	Policy create(Policy payload);
	Policy patch(Long id);
	Policy delete(Long id);

}
