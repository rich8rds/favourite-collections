/* Collections #2024 */
package com.favourite.collections.infrastructure.mail.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WelcomeRequestData extends EmailRequestData {
	private String displayName;
	private String activateUrl;
}
