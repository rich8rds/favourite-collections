/* Collections #2024 */
package com.favourite.collections.infrastructure.mail.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favourite.collections.infrastructure.mail.data.EmailRequestData;
import com.favourite.collections.infrastructure.mail.data.WelcomeRequestData;
import com.favourite.collections.infrastructure.mail.service.EmailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "MailApi")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class MailApiController {
	private final EmailService emailService;

	@PostMapping
	public String sendHtmlEmail(@RequestBody EmailRequestData requestData) {
		return emailService.sendEmail(requestData);
	}

	@PostMapping("welcome")
	public String sendHtmlEmailWelcome(@RequestBody WelcomeRequestData requestData) {
		return emailService.sendWelcomeEmail(requestData);
	}
}
