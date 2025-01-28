package com.insurances.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurances.manager.domain.entity.PolicyEntity;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {

}
