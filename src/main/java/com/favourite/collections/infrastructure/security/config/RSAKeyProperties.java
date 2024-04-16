/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "rsa")
@Getter
@Setter
public class RSAKeyProperties {
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
}
