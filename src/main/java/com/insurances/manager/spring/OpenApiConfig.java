package com.insurances.manager.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
			.title("Insurance API")
			.version("0.0.1")
		).components(new Components()
			.addSecuritySchemes("bearer-token", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
		);
	}

}
