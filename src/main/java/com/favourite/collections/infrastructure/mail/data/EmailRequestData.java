/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.mail.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailRequestData {
	private String from;
	private String to;
	private String subject;
	private String body;

	public String validate() {
		if (this.from == null) {
			return "From email cannot be null";
		} else if (this.to == null || this.to.isBlank()) {
			return "Email cannot be null or blank";
		} else if (this.subject == null || this.subject.isBlank()) {
			return "Subject for email cannot be null or blank";
		} else if (this.body == null || this.body.isBlank()) {
			return "Email body must have a content.";
		}
		return "valid";
	}
}
