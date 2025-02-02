/* Collections #2025 */
package com.favourite.collections.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/gateway")
public class Controller {

	@GetMapping
	public ResponseEntity<String> get() {
		return ResponseEntity.ok("Hello World");
	}
}
