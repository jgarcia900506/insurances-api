package com.insurances.manager.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurances.manager.controller.dto.PolicyDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/policies")
public class PolicyController {

	@Operation(
		summary = "Fetch all policies",
		description = "Return a policy collection"
	)
	@GetMapping(produces = "application/json")
	public ResponseEntity<Iterable<PolicyDTO>> fetchAll() {
		return ResponseEntity.ok(Collections.emptyList());
	}

	@Operation(
		summary = "Fetch specific policy",
		description = "Return a specific policy by id"
	)
	@GetMapping(path = {"/{id}"}, produces = "application/json")
	public ResponseEntity<PolicyDTO> fetchById(@PathVariable Long id) {
		return ResponseEntity.ok(null);
	}

	@Operation(
		summary = "create a new policy",
		description = "Create a new policy and return generated record"
	)
	@PostMapping(consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<PolicyDTO> create(@Valid @RequestBody PolicyDTO record) {
		return ResponseEntity.ok(record);
	}
}
