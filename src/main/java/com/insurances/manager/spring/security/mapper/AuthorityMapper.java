package com.insurances.manager.spring.security.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.domain.entity.AuthorityEntity;
import com.insurances.manager.services.model.Authority;

@Mapper
public interface AuthorityMapper {

	final static AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

	Authority map(AuthorityEntity record);
	Collection<Authority> map(List<AuthorityEntity> records);

	@InheritInverseConfiguration
	AuthorityEntity map(Authority model);

}
