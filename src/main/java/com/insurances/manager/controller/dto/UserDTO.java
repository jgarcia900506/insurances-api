package com.insurances.manager.controller.dto;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class UserDTO implements UserDetails {

	private Long id;
	private String username;
	private Collection<AuthorityDTO> authorities;
	
	private static final long serialVersionUID = -3147226731429633975L;

	@Override
	@JsonIgnore
	public String getPassword() {
		return null;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return Boolean.TRUE;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return Boolean.TRUE;
	}

}
