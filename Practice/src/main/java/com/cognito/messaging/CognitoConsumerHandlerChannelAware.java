package com.cognito.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;

import com.rabbitmq.client.Channel;

public class CognitoConsumerHandlerChannelAware implements ChannelAwareMessageListener {

	public void onMessage(Message message, Channel channel) throws Exception {
		JsonMessageConverter jmc = new JsonMessageConverter();
		//ClientJobMessage u = (ClientJobMessage)jmc.fromMessage(message);
		System.out.println("Respone from '" + message.getMessageProperties().getAppId()+"'");
		//System.out.println("Response : " + u);
		System.out.println("Response : " + message);
		//channel.basicReject(1000, true);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

}
