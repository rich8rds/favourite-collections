package com.favourite.collections.notification.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ThymeleafConfig {

    private final TemplateEngine templateEngine;

    public String process(String templatePath, Context context) {
        try {
            log.info("Processing template: {}", templatePath);
            return templateEngine.process(templatePath, context);
        } catch (Exception e) {
            log.error("Error processing template path: {}", templatePath, e);
            throw new RuntimeException("Failed to process email template", e);
        }
    }
}
