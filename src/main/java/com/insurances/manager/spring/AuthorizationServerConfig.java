package com.insurances.manager.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.insurances.manager.spring.security.filter.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {

	@Autowired
	@Qualifier("customAuthenticationProvider")
	private AuthenticationProvider provider; 

	@Autowired
	private JwtAuthorizationFilter filter;

	@Bean
	@Order(1)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf((cfg) -> cfg.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers(
					"/v3/api-docs/**",
	        		"/swagger-ui/**",
	        		"/swagger-ui.html",
	        		"/csrf/**",
	        		"/login"
				).permitAll()
				.anyRequest()
					.authenticated()
		)
		.addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(session -> session
           .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)
        );

		return http.build();
    }

	@Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
        		.getSharedObject(AuthenticationManagerBuilder.class)
        		.authenticationProvider(provider)
        		.build();
    }

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
