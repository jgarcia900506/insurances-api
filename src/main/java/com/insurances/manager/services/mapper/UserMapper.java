package com.insurances.manager.services.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.domain.entity.UserEntity;
import com.insurances.manager.services.model.User;
import com.insurances.manager.spring.security.mapper.AuthorityMapper;

@Mapper(uses = {AuthorityMapper.class})
public interface UserMapper {

	final static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User map(UserEntity record);
	
	@InheritInverseConfiguration
	UserEntity map(User model);

}
