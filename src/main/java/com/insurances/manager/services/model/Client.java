package com.insurances.manager.services.model;

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
public class Client {

	private Long id;
	private String dni;
	private String name;
	private String lastname;
	private String email;
	private String phone;
	private User user;
}
