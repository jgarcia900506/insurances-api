package com.insurances.manager.services.mapper;

import java.util.Collection;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.domain.entity.ClientEntity;
import com.insurances.manager.services.model.Client;

@Mapper
public interface ClientMapper {

	static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	Client map(ClientEntity record);
	Collection<Client> map(Iterable<ClientEntity> records);

	@InheritInverseConfiguration
	@Mapping(target = "policies", ignore = true)
	ClientEntity map(Client model);
}
