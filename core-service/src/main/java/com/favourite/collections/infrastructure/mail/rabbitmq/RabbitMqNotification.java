/* Collections #2024 */
package com.favourite.collections.infrastructure.mail.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMqNotification {

	@Value("${app.rabbitmq.topic.exchange}")
	private String topicExchangeName;

	private final RabbitTemplate rabbitTemplate;

	public void sendNotification(String emailRequestData) {
		log.info("Sending message...");
		try {
			this.rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", emailRequestData);
		} catch (Exception e) {
			log.error("Error sending message", e);
		}
	}
}
