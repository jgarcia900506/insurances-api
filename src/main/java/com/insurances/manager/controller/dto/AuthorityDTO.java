package com.insurances.manager.controller.dto;

import org.springframework.security.core.GrantedAuthority;

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
public class AuthorityDTO implements GrantedAuthority {

	private Short id;
	private String name;

	private static final long serialVersionUID = -7574657286605496044L;

	@Override
	public String getAuthority() {
		return name;
	}

}
