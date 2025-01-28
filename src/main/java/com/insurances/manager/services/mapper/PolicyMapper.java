package com.insurances.manager.services.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.insurances.manager.domain.entity.PolicyEntity;
import com.insurances.manager.services.model.Policy;

@Mapper(uses = {
	ClientMapper.class
})
public interface PolicyMapper {

	final static PolicyMapper INSTANCE = Mappers.getMapper(PolicyMapper.class);

	Policy map(PolicyEntity record);
	Collection<Policy> map(List<PolicyEntity> records);

	@InheritInverseConfiguration
	PolicyEntity map(Policy model);

}
