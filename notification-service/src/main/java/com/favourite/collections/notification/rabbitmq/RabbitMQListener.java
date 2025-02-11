package com.favourite.collections.notification.rabbitmq;

import com.favourite.collections.commons.core.config.RabbitMQConfig;
import com.favourite.collections.commons.useradmin.data.EmailNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.favourite.collections.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMQListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = RabbitMQConfig.OTP_QUEUE)
    public void receiveMessage(EmailNotificationRequest request) {
        log.info("Received notification request (OTP): {}", request.getType());
        this.notificationService.sendOTPForVerification(request);
    }


    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void receiveInvitationMessage(EmailNotificationRequest request) {
        log.info("Received email notification request: {}", request);
        this.notificationService.sendOTPForVerification(request);
    }
}
