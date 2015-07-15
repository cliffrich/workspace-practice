package com.cognitomobile.ffiq.rabbit.integration;


import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author davidy
 */
@Component
public interface MessageSupport {
    String routingKey(String tenant, Object o, MessageSource source);
    String exchangeName(Exchanges e);
    Message buildMessage(String tenant, Object o, String errorMsg, MessageSource source);
    Message buildMessage(String tenant, Object o, MessageSource source);
    Message buildMessage(String tenant, Object o, Map<String, Object> headers, MessageSource source);
    Message buildMessage(Map<String, Object> headers, Object o);
}
