package com.cognitomobile.ffiq.rabbit.integration;

/**
 * An exception indicating the recipient isn't ready to process a broker message.
 *
 * @author Kevin Poalses
 */
public class NotReadyForMessageException extends RuntimeException {

    public NotReadyForMessageException(final String exceptionMessage) {
        super(exceptionMessage);
    }


    public NotReadyForMessageException(final String exceptionMessage, final Throwable cause) {
        super(exceptionMessage, cause);
    }

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = 928385551297825856L;
}
