//package com.favourite.collections.notification.config;
//
//import com.favourite.collections.commons.useradmin.data.MailClientRequest;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import java.io.UnsupportedEncodingException;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class GmailMailConfig {
//
//    @Value("${app.host.email}")
//    private String email;
//
//    @Value("${app.host.name}")
//    private String name;
//
//    private final JavaMailSender mailSender;
//
//    public void sendEmail(MailClientRequest request) {
//        String to = request.getTo();
//        String subject = request.getSubject();
//        String message = request.getMessage();
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//        try {
//            helper.setFrom(email, name);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(message, true);
//            mailSender.send(mimeMessage);
//        } catch (MessagingException | UnsupportedEncodingException e) {
//            log.error("An error occurred while sending an email to address : {}; error: {}", to, e.getMessage());
//        }
//        log.info("Email Sent to: {}", to);
//    }
//}
