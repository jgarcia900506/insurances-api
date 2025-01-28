package com.insurances.manager.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurances.manager.domain.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Short> {

	Optional<AuthorityEntity> findByName(String name);

}
