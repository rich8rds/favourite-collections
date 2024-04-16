/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.security.data;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginData {
	private final String email;
	private final String password;
}
