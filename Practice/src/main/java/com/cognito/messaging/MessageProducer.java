package com.cognito.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author rcliff
 * 
 * Another test class to test through a 'main' method rather than JUnit.
 * Created specifically to deploy on the POC server and test independently
 */
public class MessageProducer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(CognitoMessageProducerConfiguration.class);
		AmqpTemplate template = appContext.getBean(AmqpTemplate.class);
		MessageProducer producer = new MessageProducer();
		try {
			for(int i=0;i<5;i++){
				String msg = "Test message " + i+1;
				System.out.println("Sending the following message "+i+1+" ...\n"+msg.toString());
				template.convertAndSend(msg);
			}
			/*while(true){ // Just to receive responses
				
			}*/
			appContext.close();
 
		} catch (AmqpException e) {
			System.out.println( "Test failed: " + e.getLocalizedMessage() );
		}
	}
	

}
