package com.insurances.manager.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insurances.manager.domain.entity.PolicyEntity;

import jakarta.transaction.Transactional;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {

	List<PolicyEntity> findAllByClientId(Long id);
	
	@Modifying
	@Transactional
	@Query("update PolicyEntity p set p.status = :status where p.id = :id")
	int updatePolicyStatus(@Param(value = "id") Long id, @Param(value = "status") Boolean status);
	
}
