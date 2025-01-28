package com.insurances.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurances.manager.controller.dto.PolicyDTO;
import com.insurances.manager.controller.mapper.PolicyDTOMapper;
import com.insurances.manager.services.PolicyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/policies")
public class PolicyController {

	@Autowired
	public PolicyService service;

	private PolicyDTOMapper mapper = PolicyDTOMapper.INSTANCE;

	@Operation(
		summary = "Fetch all policies",
		description = "Return a policy collection",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@GetMapping(produces = "application/json")
	public ResponseEntity<Iterable<PolicyDTO>> fetchAll() {
		return ResponseEntity.ok(mapper.map(service.fetchAll()));
	}

	@Operation(
		summary = "Fetch specific policy",
		description = "Return a specific policy by id",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@GetMapping(path = {"/{id}"}, produces = "application/json")
	public ResponseEntity<PolicyDTO> fetchById(
		@Parameter(name = "id", description = "Client id to fetch", required = true)
		@PathVariable(name = "id") Long id
	) {
		return ResponseEntity.ok(mapper.map(service.fetchById(id)));
	}

	@Operation(
		summary = "create a new policy",
		description = "Create a new policy and return generated record",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@PostMapping(consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<PolicyDTO> create(@Valid @RequestBody PolicyDTO record) {
		return ResponseEntity.ok(mapper.map(service.create(mapper.map(record))));
	}
}
