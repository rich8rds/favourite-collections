/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.favourite.collections.infrastructure.security.config.RSAKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class FavouriteCollectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteCollectionsApplication.class, args);
	}
}
