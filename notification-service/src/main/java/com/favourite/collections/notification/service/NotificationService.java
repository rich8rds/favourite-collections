package com.favourite.collections.notification.service;

import com.favourite.collections.commons.useradmin.data.EmailNotificationRequest;
import com.favourite.collections.commons.useradmin.data.MailClientRequest;
import com.favourite.collections.commons.useradmin.enums.NotificationType;
import com.favourite.collections.notification.config.GmailMailConfig;
import com.favourite.collections.notification.config.ThymeleafConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final GmailMailConfig mail;
    private final ThymeleafConfig thymeleafConfig;

    public void sendEmail(EmailNotificationRequest request) {
        log.info("Processing email notification: {}", request.getType());
        try {
            Context context = new Context();
            context.setVariable("name", request.getName());

            if (request.getTemplateData().containsKey("otp")) {
                context.setVariable("otp", request.getTemplateData().get("otp"));
            }

            String template = getTemplateForNotificationType(request.getType());
            String subject = getSubjectForNotificationType(request.getType());

            String output = thymeleafConfig.process(template, context);
            log.info("Processed template: {}", template);

            MailClientRequest mailRequest = MailClientRequest.fromSendEmailRequest(
                    request.getTo(),
                    subject,
                    output
            );
            mail.sendEmail(mailRequest);

            log.info("Successfully sent email to: {}", request.getTo());
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", request.getTo(), e.getMessage(), e);
        }
    }

    private String getTemplateForNotificationType(NotificationType type) {
        return switch (type) {
            case VERIFICATION_OTP -> "verification-email";
            case PASSWORD_RESET_OTP -> "password-reset";
            case WELCOME -> "welcome-email";
            case PASSWORD_CHANGED -> "password-changed";
        };
    }

    private String getSubjectForNotificationType(NotificationType type) {
        return switch (type) {
            case VERIFICATION_OTP -> "Email Verification";
            case PASSWORD_RESET_OTP -> "Password Reset";
            case WELCOME -> "Welcome to Favourite Collections";
            case PASSWORD_CHANGED -> "Password Changed Successfully";
        };
    }
}
