package com.cognitomobile.ffiq.rabbit.integration;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;


public class UnsupportedMessageException extends AmqpRejectAndDontRequeueException {

    public UnsupportedMessageException(final String exceptionMessage) {
        super(exceptionMessage);
    }

    public UnsupportedMessageException(final String exceptionMessage, final Throwable cause) {
        super(exceptionMessage, cause);
    }

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = -6232256340477172264L;
}
