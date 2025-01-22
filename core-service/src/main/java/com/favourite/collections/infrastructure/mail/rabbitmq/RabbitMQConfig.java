/* Collections #2024 */
package com.favourite.collections.infrastructure.mail.rabbitmq;

import com.favourite.collections.commons.core.json.FromJsonHelper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.favourite.collections.infrastructure.mail.data.EmailRequestData;
import com.favourite.collections.infrastructure.mail.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

	@Value("${app.rabbitmq.topic.exchange}")
	private String topicExchangeName;

	@Value("${app.rabbitmq.queue.name}")
	private String queueName;

	private final EmailService emailService;

	private final FromJsonHelper fromJsonHelper;

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(RabbitMQConfig receiver) {
		return new MessageListenerAdapter(receiver, "listen");
	}

	@Bean
	public Queue createFavouriteNotificationQueue() {
		return new Queue(queueName, false);
	}

	@RabbitListener(queues = "${app.rabbitmq.queue.name:favourite-notification}", concurrency = "${omni.notification.rabbitmq.consumer:1}")
	public void listen(String emailRequestData) {
		log.info("Message read from queue : {}", emailRequestData);
		log.info("Gladly received!");
		try {
			this.emailService.sendEmail(this.fromJsonHelper.fromJson(emailRequestData, EmailRequestData.class));
		} catch (Exception e) {
			log.error("Rabbit Listener method: {}", e.getMessage());
		}
	}

	private void blahblah(String emailRequestData) {
		log.info("private blah blah: {}", emailRequestData);
	}
}
