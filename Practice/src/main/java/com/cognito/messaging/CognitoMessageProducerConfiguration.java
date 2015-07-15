package com.cognito.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rcliff
 *
 * Rabbit configuration for Cognito producer of messages
 */
@Configuration
public class CognitoMessageProducerConfiguration extends AbstractCognitoRabbitConfiguration{

	/* The server's template will by default send to the direct exchange named 'RequestExchange'
	 * 
	 */
	protected void configureRabbitTemplate(RabbitTemplate rabbitTemplate) {
		rabbitTemplate.setExchange(cognitoExchange);
		rabbitTemplate.setQueue(cognitoRequestQueue);
		rabbitTemplate.setRoutingKey(cognitoRequestQueue);
	}
    /**
     * Binds the request queue to the request exchange. 
     */
    @Bean
    public Binding cognitoRequestBinding() {
        return BindingBuilder.bind(cognitoRequestQueue()).to(cognitoExchange()).with(cognitoRequestQueue);
    }
	@Bean
	public DirectExchange cognitoExchange() {
		return new DirectExchange(cognitoExchange, true, false);
	}
	/**
	 * We don't need to define any binding for the cognito request queue, since it's relying
	 * on the default (no-name) direct exchange to which every queue is implicitly bound.	 
	 */
	@Bean
	public Queue cognitoRequestQueue(){
		return new Queue(cognitoRequestQueue, true, false, false);
	}
}
