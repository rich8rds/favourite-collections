/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.mail.service;

import com.favourite.collections.infrastructure.mail.data.EmailRequestData;
import com.favourite.collections.infrastructure.mail.data.WelcomeRequestData;

public interface EmailService {
	String sendEmail(EmailRequestData data);

	String sendEmailWithAttachment(EmailRequestData data, String attachmentPath);

	String sendWelcomeEmail(WelcomeRequestData requestData);
}
