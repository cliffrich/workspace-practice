package com.cognito.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CognitoMessageRequesterConfiguration extends AbstractCognitoSynchronouwRabbitConfiguration{
	@Value("${messaging.sync.exchange}")
	String syncExchange;
	@Value("${messaging.sync.request.queue}")
    String syncRequestQueue;
	@Value("${messaging.sync.response.queue}")
    String syncResponseQueue;
    
	protected void configureSynchronousRabbitTemplate(RabbitTemplate rabbitTemplate) {
		rabbitTemplate.setExchange(syncExchange);
		rabbitTemplate.setQueue(syncRequestQueue);
		rabbitTemplate.setRoutingKey(syncRequestQueue);
	}
}
