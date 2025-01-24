package com.favourite.collections.commons.useradmin.data;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
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