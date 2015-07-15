package com.cognito.messaging;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rcliff
 *
 * Rabbit configuration for GCDI when it acts as a consumer of response messages coming from cognito
 */
@Configuration
public class CognitoMessageConsumerConfiguration extends AbstractCognitoRabbitConfiguration{
	@Value("${messaging.concurrentConsumers}")
	int concurrentConsumers;

	@Bean
	public SimpleMessageListenerContainer congnitoPubSubConsumer(){
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueues(cognitoMessageRequestQueue());
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(channelAwareMessageListener());
		
		container.setConcurrentConsumers(concurrentConsumers);
		container.setQueueNames(cognitoRequestQueue);
		container.setChannelTransacted(true);
		container.setDefaultRequeueRejected(true);
		container.setAutoStartup(true);
		//container.setErrorHandler(errorHandler);
		container.setMessageListener(new CognitoConsumerHandlerChannelAware());
		return container;
	}
	@Bean
	public ChannelAwareMessageListener channelAwareMessageListener(){
		return new CognitoConsumerHandlerChannelAware();
	}


	/* The server's template will by default send to the direct exchange named 'cognitoResponseExchange'
	 * 
	 */
	protected void configureRabbitTemplate(RabbitTemplate rabbitTemplate) {
		rabbitTemplate.setExchange(cognitoExchange);
		rabbitTemplate.setQueue(cognitoRequestQueue);
		rabbitTemplate.setRoutingKey(cognitoRequestQueue);
	}
    /**
     * Binds the response queue to the response exchange. 
     */
    @Bean
    public Binding cognitoMessageConsumerBinding() {
        return BindingBuilder.bind(cognitoMessageRequestQueue()).to(cognitoExchange()).with(cognitoRequestQueue);
    }
	@Bean
	public DirectExchange cognitoExchange() {
		return new DirectExchange(cognitoExchange, true, false);
	}

	@Bean
	public Queue cognitoMessageRequestQueue(){
		return new Queue(cognitoRequestQueue, true, false, false);
	}
}
