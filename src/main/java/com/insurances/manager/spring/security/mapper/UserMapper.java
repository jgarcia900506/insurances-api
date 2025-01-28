package com.insurances.manager.spring.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.controller.dto.UserDTO;
import com.insurances.manager.domain.entity.UserEntity;

@Mapper(uses = { AuthorityMapper.class })
public interface UserMapper {

	final static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO map(UserEntity entity);

}
