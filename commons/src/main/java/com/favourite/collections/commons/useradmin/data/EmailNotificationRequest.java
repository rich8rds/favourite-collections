package com.favourite.collections.commons.useradmin.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.favourite.collections.commons.useradmin.enums.NotificationType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@ToString
public class EmailNotificationRequest implements Serializable {
    private String to;
    private String name;
    private NotificationType type;
    private Map<String, Object> templateData;

    @JsonCreator
    public EmailNotificationRequest(
            @JsonProperty("to") String to,
            @JsonProperty("name") String name,
            @JsonProperty("type") NotificationType type,
            @JsonProperty("templateData") Map<String, Object> templateData) {
        this.to = to;
        this.name = name;
        this.type = type;
        this.templateData = templateData;
    }

    public static EmailNotificationRequest verificationOtp(String email, String name, String otp) {
        return EmailNotificationRequest.builder()
                .to(email)
                .name(name)
                .type(NotificationType.VERIFICATION_OTP)
                .templateData(Map.of("otp", otp))
                .build();
    }

    public static EmailNotificationRequest passwordResetOtp(String email, String name, String otp) {
        return EmailNotificationRequest.builder()
                .to(email)
                .name(name)
                .type(NotificationType.PASSWORD_RESET_OTP)
                .templateData(Map.of("otp", otp))
                .build();
    }

    public static EmailNotificationRequest welcome(String email, String name) {
        return EmailNotificationRequest.builder()
                .to(email)
                .name(name)
                .type(NotificationType.WELCOME)
                .templateData(Map.of())
                .build();
    }

    public static EmailNotificationRequest passwordChanged(String email, String name) {
        return EmailNotificationRequest.builder()
                .to(email)
                .name(name)
                .type(NotificationType.PASSWORD_CHANGED)
                .templateData(Map.of())
                .build();
    }
}
