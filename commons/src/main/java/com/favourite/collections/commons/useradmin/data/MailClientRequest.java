package com.favourite.collections.commons.useradmin.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailClientRequest implements Serializable {
    private String to;
    private String subject;
    private String message;

    public static MailClientRequest fromSendEmailRequest(String email, String subject, String body) {
        MailClientRequest mailClientRequest = new MailClientRequest();
        mailClientRequest.setTo(email);
        mailClientRequest.setSubject(subject);
        mailClientRequest.setMessage(body);
        return mailClientRequest;
    }
}