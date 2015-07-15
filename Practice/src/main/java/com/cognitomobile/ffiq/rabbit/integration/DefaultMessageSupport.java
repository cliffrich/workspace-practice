package com.cognitomobile.ffiq.rabbit.integration;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author davidy
 */
@Component
public class DefaultMessageSupport implements MessageSupport {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMessageSupport.class);

    private MessageBuilder getMessageBuilder(String tenant, Object o, MessageSource source) {
        return MessageBuilder
                            .withPayload(o)
                            .setHeader(MessageHeaders.Tenant.getName(), tenant)
                            .setHeader(MessageHeaders.RoutingKey.getName(), routingKey(tenant, o, source));
    }

    private MessageBuilder addHeaders(MessageBuilder builder, Map<String, Object> headers) {
        if (headers != null
                && headers.size() > 0) {

            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                builder = builder.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return builder;
    }

    @Override
    public String routingKey(String tenant, Object o, MessageSource source) {
        //ToDo fix getType and getSubType and src
        String routingKeyStr = tenant + "." + o.getClass().getSimpleName() + ".*.*.*." + source.key();
        LOG.debug("routingKey for tenant: {}, object: {} is :{}", new Object[] {tenant, o, routingKeyStr});
        return routingKeyStr;
    }

    @Override
    public String exchangeName(Exchanges e) {
        return e.getName();
    }


    @Override
    public Message buildMessage(String tenant, Object o, String errorMsg, MessageSource source) {
        MessageBuilder builder = getMessageBuilder(tenant, o, source);
        if (!StringUtils.isEmpty(errorMsg)) {
            builder = builder.setHeader(MessageHeaders.Error.getName(), errorMsg);
        }
        return builder.build();
    }

    @Override
    public Message buildMessage(String tenant, Object o, MessageSource source) {
        MessageBuilder builder = getMessageBuilder(tenant, o, source);
        return builder.build();
    }

    @Override
    public Message buildMessage(String tenant, Object o, Map<String, Object> headers, MessageSource source) {
        MessageBuilder builder = getMessageBuilder(tenant, o, source);
        builder = addHeaders(builder, headers);
        return builder.build();
    }

    @Override
    public Message buildMessage(Map<String, Object> headers, Object o) {
        MessageBuilder builder = MessageBuilder.withPayload(o);
        builder = addHeaders(builder, headers);
        return builder.build();
    }
}
