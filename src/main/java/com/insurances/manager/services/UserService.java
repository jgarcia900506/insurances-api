package com.insurances.manager.services;

import com.insurances.manager.services.model.User;

public interface UserService {

	User fetchById(Long id);
	User create(User user);
	User delete(Long id);

}
