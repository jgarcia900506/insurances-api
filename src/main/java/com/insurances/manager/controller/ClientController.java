package com.insurances.manager.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurances.manager.controller.dto.ClientDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = {"/clients"})
public class ClientController {

	@Operation(
		summary = "Fetch all clients",
		description = "Return a client collection"
	)
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<Iterable<ClientDTO>> featchAll() {
		return ResponseEntity.ok(Collections.emptyList());
	}

	@Operation(
		summary = "Fetch specific client",
		description = "Return a specific clients by id"
	)
	@GetMapping(path = {"/{id}"}, produces = {"application/json"})
	public ResponseEntity<ClientDTO> fetchById(
		@Parameter(name = "id", description = "Client id to fetch", required = true)
		@PathVariable Long id
	) {
		return ResponseEntity.ok(null);
	}

	@Operation(
		summary = "create a new client",
		description = "Create a new client and return generated record"
	)
	@PostMapping(consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO record) {
		return ResponseEntity.ok(record);
	}

}
