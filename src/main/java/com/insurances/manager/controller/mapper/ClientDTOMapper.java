package com.insurances.manager.controller.mapper;

import java.util.Collection;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.controller.dto.ClientDTO;
import com.insurances.manager.services.model.Client;

@Mapper(uses = {UserDTOMapper.class}, componentModel = "default")
public interface ClientDTOMapper {

	static final ClientDTOMapper INSTANCE = Mappers.getMapper(ClientDTOMapper.class);
	
	ClientDTO map(Client model);
	Iterable<ClientDTO> map(Collection<Client> models);

	@InheritInverseConfiguration
	Client map(ClientDTO payload);

}
