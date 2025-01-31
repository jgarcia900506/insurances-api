package com.insurances.manager.spring.security.service;

import com.insurances.manager.services.model.User;

public interface AuthenticationService {

	User authenticate(String username, String password);

}
