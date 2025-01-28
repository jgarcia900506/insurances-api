package com.insurances.manager.controller.mapper;

import com.insurances.manager.controller.dto.ClientDTO;
import com.insurances.manager.controller.dto.UserDTO;

public class ObjectFactory {

	UserDTO createUserPayload() {
		return UserDTO.builder().build();
	}

	ClientDTO createClientPayload() {
		return ClientDTO.builder().build();
	}

}
