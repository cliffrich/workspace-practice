package com.cognitomobile.ffiq.rabbit.integration;

import java.util.EnumSet;

/**
 * @author davidy
 */
public enum MessageHeaders {
    Tenant("TENANT"),
    RoutingKey("ROUTING_KEY"),
    Error("ERROR"),
    UAOverride("UAOVERRIDE"),
    UserId("USER_ID"),
    TraceId("TRACE_ID"),
    Exchange("EXCHANGE"),
    Queue("QUEUE"),
    Timestamp("TIMESTAMP"),
    RetryCount("RETRY_COUNT"),
    ImmediateDlq("IMMEDIATE_DLQ"),
    LogicalVirtualHost("LOGICAL_VIRTUAL_HOST"),
    // amqp message properties
    amqpDeliveryMode("amqp_deliveryMode"),
    amqpDeliveryTag("amqp_deliveryTag"),
    amqpMessageId("amqp_messageId"),
    amqpReceivedExchange("amqp_receivedExchange"),
    amqpReceivedRoutingKey("amqp_receivedRoutingKey"),
    amqpRedelivered("amqp_redelivered"),
    amqpTimestamp("amqp_timestamp"),
    amqpContentType("content-type"),
    amqpId("id"),
    amqpEpochTime("timestamp");

    private String name;
    public static EnumSet<MessageHeaders> amqpPropertiesGroup = EnumSet.of(amqpDeliveryMode,
            amqpDeliveryTag,
            amqpMessageId,
            amqpReceivedExchange,
            amqpReceivedRoutingKey,
            amqpRedelivered,
            amqpTimestamp,
            amqpContentType,
            amqpId,
            amqpEpochTime);

    MessageHeaders(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}