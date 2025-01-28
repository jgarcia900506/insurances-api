package com.insurances.manager.spring.security.service;

import com.insurances.manager.controller.dto.UserDTO;

public interface AuthenticationService {

	UserDTO authenticate(String username, String password);

}
