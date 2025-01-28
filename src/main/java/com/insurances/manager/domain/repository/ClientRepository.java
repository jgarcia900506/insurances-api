package com.insurances.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurances.manager.domain.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
