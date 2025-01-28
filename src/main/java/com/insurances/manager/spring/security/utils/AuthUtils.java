package com.insurances.manager.spring.security.utils;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

public class AuthUtils {

	private static AuthUtils instance = null;

	private final String[] EMPTY_AUTHS = new String[0];

	private AuthUtils()throws Exception { }
	
	public String[] AuthoritiesToClaims(Collection<? extends GrantedAuthority> authorities) {
		String[] result = (authorities==null)? EMPTY_AUTHS : new String[authorities.size()];
		
		if(authorities.size() > 0) {
			authorities.stream().map(new Function<GrantedAuthority, String>() {

				@Override
				public String apply(GrantedAuthority t) {
					return t.getAuthority();
				}
				
			}).collect(Collectors.toSet()).toArray(result);
		}
		
		return result;
	}

	public static AuthUtils getInstance() {
		if(instance == null) {
			try {
				instance = new AuthUtils();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}

}
