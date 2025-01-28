package com.insurances.manager.controller.mapper;

import java.util.Collection;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.controller.dto.AuthorityDTO;
import com.insurances.manager.services.model.Authority;

@Mapper(componentModel = "default")
public interface AuthorityDTOMapper {

	final static AuthorityDTOMapper INSTANCE = Mappers.getMapper(AuthorityDTOMapper.class);

	AuthorityDTO map(Authority record);
	Iterable<AuthorityDTO> map(Collection<Authority> models);
	
	@InheritInverseConfiguration
	Authority map(AuthorityDTO payload);
	Collection<Authority> map(Iterable<AuthorityDTO> payload);

}
