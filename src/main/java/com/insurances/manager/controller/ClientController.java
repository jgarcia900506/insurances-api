package com.insurances.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurances.manager.controller.dto.ClientDTO;
import com.insurances.manager.controller.mapper.ClientDTOMapper;
import com.insurances.manager.services.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = {"/clients"})
public class ClientController {

	@Autowired
	private ClientService service;

	private ClientDTOMapper mapper = ClientDTOMapper.INSTANCE;

	@Operation(
		summary = "Fetch all clients",
		description = "Return a client collection",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<Iterable<ClientDTO>> featchAll() {
		return ResponseEntity.ok(mapper.map(service.fetchAll()));
	}

	@Operation(
		summary = "Fetch specific client",
		description = "Return a specific clients by id",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@GetMapping(path = {"/{id}"}, produces = {"application/json"})
	public ResponseEntity<ClientDTO> fetchById(
		@Parameter(name = "id", description = "Client id to fetch", required = true)
		@PathVariable(name = "id") Long id
	) {
		return ResponseEntity.ok(mapper.map(service.fetchById(id)));
	}

	@Operation(
		summary = "Create a new client",
		description = "Create a new client and return generated record",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@PostMapping(consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO payload) {
		return ResponseEntity.ok(mapper.map(service.create(mapper.map(payload))));
	}

	@Operation(
		summary = "Delete a client",
		description = "Delete client and return removed record",
		security = {
			@SecurityRequirement(name = "bearer-token")
		}
	)
	@DeleteMapping(path = {"/{id}"}, produces = {"application/json"})
	public ResponseEntity<ClientDTO> delete(
		@Parameter(name = "id", description = "Client id to delete", required = true)
		@PathVariable(name = "id") Long id
	) {
		return ResponseEntity.ok(mapper.map(service.delete(id)));
	}
	
}
