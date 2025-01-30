package com.insurances.manager.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurances.manager.domain.entity.PolicyEntity;
import com.insurances.manager.domain.repository.PolicyRepository;
import com.insurances.manager.services.PolicyService;
import com.insurances.manager.services.mapper.PolicyMapper;
import com.insurances.manager.services.model.Policy;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository repository;

	private PolicyMapper mapper = PolicyMapper.INSTANCE;

	@Override
	public Collection<Policy> fetchAll(Long id) {
		return mapper.map(repository.findAllByClientId(id));
	}

	@Override
	public Policy fetchById(Long id) {
		return mapper.map(repository.getReferenceById(id));
	}

	@Override
	public Policy create(Policy payload) {
		PolicyEntity record = mapper.map(payload);
		return mapper.map(repository.save(record));
	}

	@Override
	public Policy patch(Long id) {
		PolicyEntity record = repository.getReferenceById(id);
		record.setStatus(!record.isStatus());
		repository.updatePolicyStatus(id, record.isStatus());

		return mapper.map(record);
	}

	@Override
	public Policy delete(Long id) {
		PolicyEntity record = repository.getReferenceById(id);
		repository.deleteById(record.getId());

		return mapper.map(record);
	}
	
}
