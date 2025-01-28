package com.insurances.manager.controller.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.controller.dto.UserDTO;
import com.insurances.manager.services.model.User;

@Mapper(uses = {AuthorityDTOMapper.class}, componentModel = "default")
public interface UserDTOMapper {

	final static UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

	//@Mapping(source = "authorities", target = "authorities", qualifiedByName = "getAuthorities")
	UserDTO map(User model);
	
	@InheritInverseConfiguration
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	User map(UserDTO payload);

	/*
	@Named("getAuthorities")
	default Iterable<AuthorityDTO> resolveClientForQualifier(Collection<Authority> models, @Context AuthorityMapper mapper) {
		return mapper.map(null);
	}
	*/

}
