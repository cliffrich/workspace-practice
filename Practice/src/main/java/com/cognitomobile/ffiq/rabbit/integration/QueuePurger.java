package com.cognitomobile.ffiq.rabbit.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * @author liz.swallow
 * (c) Cognito Ltd. All rights reserved.
 */
public class QueuePurger {
    private static final Logger LOG = LoggerFactory.getLogger(QueuePurger.class);

    private CachingConnectionFactory connectionFactory;

    public QueuePurger(final CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void clearQueue(final String queueName) {
        LOG.debug("Purging queue " + queueName);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.purgeQueue(queueName, true);
        LOG.info("Successfully purged queue " + queueName);
    }

}
