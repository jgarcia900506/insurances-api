package com.insurances.manager.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

	private Long id;

	@NotBlank(message = "DNI is required")
	private String dni;

	@NotBlank(message = "Name is required")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Name can only contain alphanumeric characters")
	private String name;

	@NotBlank(message = "Lastname is required")
	@Pattern(regexp = "^[A-Za-z]+$", message = "Lastname can only contain alphanumeric characters")
	private String lastname;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email is set")
	private String email;

	@NotEmpty(message = "A phone is required at least")
	private String phone;

	private UserDTO user;

}
