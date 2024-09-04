/* Collections #2024 */
package com.favourite.collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FavouriteCollectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteCollectionsApplication.class, args);
	}
}
