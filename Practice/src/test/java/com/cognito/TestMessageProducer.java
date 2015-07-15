package com.cognito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:applicationMessagingContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMessageProducer {
	@Autowired 
	private AmqpAdmin admin;
	@Autowired 
	private AmqpTemplate rabbitTemplate;

	@Test 
	public void simpleProducerTest() {
		try {
			for(int i=1;i<5;i++){
				String msg = "Test message " + i;
				System.out.println("Sending the following message "+i+" ...\n"+msg.toString());
				rabbitTemplate.convertAndSend(msg);
			}
			while(true){ // Just to receive responses
				
			}
 
		} catch (AmqpException e) {
			e.printStackTrace();
			Assert.fail( "Test failed: " + e.getLocalizedMessage() );
		}
	}
}
