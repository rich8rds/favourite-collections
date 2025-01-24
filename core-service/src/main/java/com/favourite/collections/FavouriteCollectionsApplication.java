/* Collections #2024 */
package com.favourite.collections;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFlux
public class FavouriteCollectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteCollectionsApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(
			@Value("${application.title}") String appTitle,
			@Value("${application.description}") String appDesciption,
			@Value("${application.version}") String appVersion
			//@Value("${application.terms.of.use}") String appTermsOfUse
			//,@Value("${application.service.url}") String url
	) {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
				//.servers(List.of(new Server().url(url)))
				.info(new Info()
						.title(appTitle)
						.version(appVersion)
						.description(appDesciption)
						.termsOfService("Terms of Use")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				 .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

	}
}
