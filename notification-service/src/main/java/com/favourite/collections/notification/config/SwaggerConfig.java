package com.favourite.collections.notification.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SwaggerConfig {

	@Value("${spring.application.name}")
	private String appName;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8791/api");
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setName(appName);
		contact.setUrl("https://www.cerpac-test-url.com");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Favourite Collections API").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage tutorials.")
				.termsOfService("https://www.cerpac-test-url.com/terms").license(mitLicense);

		return new OpenAPI()
				.security(List.of(new SecurityRequirement().addList("Bearer Authentication"),
						new SecurityRequirement().addList("Basic Authentication")))
				.components(new Components().securitySchemes(createSecuritySchemesMap())).info(info)
				.servers(List.of(devServer));
	}

	private Map<String, SecurityScheme> createSecuritySchemesMap() {

		SecurityScheme createAPIKeyScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT")
				.description("Enter a valid JWT.").scheme("bearer");

		SecurityScheme createBasicScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
				.description("Input username and password");

		Map<String, SecurityScheme> securitySchemesMap = new HashMap<>();
		securitySchemesMap.put("Basic Authentication", createBasicScheme);
		securitySchemesMap.put("Bearer Authentication", createAPIKeyScheme);

		return securitySchemesMap;
	}
}
