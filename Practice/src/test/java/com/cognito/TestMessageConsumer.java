package com.cognito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.core.ReplyToAddressCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:applicationMessagingContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageConsumer {
	@Autowired 
	private AmqpAdmin admin;
	@Autowired 
	private AmqpTemplate rabbitTemplate;
	@Value("${messaging.pubsub.request.queue}")
	String routingKey;
	@Value("messaging.sync.response.queue")
	String replyQueue;
	
	@Test
	public void simpleSynchronousConsumer(){
		rabbitTemplate.receiveAndReply(routingKey, new ReceiveAndReplyCallback<String, String>(){

			public String handle(String arg0) {	
				System.out.println("Received message : " + arg0);
				return "replying for the message received";
			}
			
		}, new ReplyToAddressCallback<String>(){

			public Address getReplyToAddress(Message arg0, String arg1) {
				return new Address(replyQueue);
			}
			
		});
	}
}
