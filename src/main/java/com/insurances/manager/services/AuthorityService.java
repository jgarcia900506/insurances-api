package com.insurances.manager.services;

import com.insurances.manager.services.model.Authority;

public interface AuthorityService {

	Authority fetchByName(String name);

}
