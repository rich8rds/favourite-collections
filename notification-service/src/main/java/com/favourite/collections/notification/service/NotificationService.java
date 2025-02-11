package com.favourite.collections.notification.service;

import com.favourite.collections.commons.useradmin.data.EmailNotificationRequest;
import com.favourite.collections.commons.useradmin.data.MailClientRequest;
import com.favourite.collections.commons.useradmin.enums.NotificationType;
//import com.favourite.collections.notification.config.GmailMailConfig;
import com.favourite.collections.notification.config.ThymeleafConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import static com.favourite.collections.notification.util.ContextUtil.NAME;
import static com.favourite.collections.notification.util.ContextUtil.OTP;


@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

//    private final GmailMailConfig mail;
    private final ThymeleafConfig thymeleafConfig;

    public void sendOTPForVerification(EmailNotificationRequest request) {
        log.info("Processing email notification: {}", request.getType());
        try {
            Context context = new Context();
            context.setVariable(NAME, request.getName());
            log.info("otp: {}", request.getOtp());
            context.setVariable(OTP, request.getOtp());

            String[] fileDetails = getTemplateForNotificationType(request.getType());
            String template = fileDetails[0];
            String subject = fileDetails[1];

            String output = thymeleafConfig.process(template, context);
            log.info("Processed template output: {}", output);

            MailClientRequest mailRequest = MailClientRequest.fromSendEmailRequest(
                    request.getTo(),
                    subject,
                    output
            );

            //mail.sendEmail(mailRequest);

            log.info("Successfully sent email to: {}", request.getTo());
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", request.getTo(), e.getMessage(), e);
        }
    }

    private String[] getTemplateForNotificationType(NotificationType type) {

        return switch (type) {
            case VERIFICATION_OTP -> templateArray("verification-email", "Email OTP verification");
            case PASSWORD_RESET_OTP -> templateArray("password-reset", "Password Reset");
            case WELCOME -> templateArray("welcome-email", "Welcome to Favourite Collections");
            case PASSWORD_CHANGED -> templateArray("password-changed", "Password Changed");
        };
    }

    private String[] templateArray(String filename, String fileSubject) {
        return new String[]{filename, fileSubject};
    }
}
