package com.insurances.manager.controller.mapper;

import java.util.Collection;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.controller.dto.PolicyDTO;
import com.insurances.manager.services.model.Client;
import com.insurances.manager.services.model.Policy;

@Mapper(componentModel = "default")
public interface PolicyDTOMapper {

	final static PolicyDTOMapper INSTANCE = Mappers.getMapper(PolicyDTOMapper.class); 

	@Mapping(source = "client.id", target = "clientId")
	PolicyDTO map(Policy model);
	Iterable<PolicyDTO> map(Collection<Policy> models);

	@InheritInverseConfiguration
	@Mapping(source = "clientId", target = "client", qualifiedByName = "getClient")
	Policy map(PolicyDTO payload);
	Collection<Policy> map(Iterable<PolicyDTO> payload);
	

	@Named("getClient")
	default Client resolveClientForQualifier(Long clientId) {
		return clientId != null ? Client.builder().id(clientId).build() : null;
	}

}
