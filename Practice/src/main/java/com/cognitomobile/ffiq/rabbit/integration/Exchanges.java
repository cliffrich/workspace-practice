package com.cognitomobile.ffiq.rabbit.integration;

/**
 * @author davidy
 */
public enum Exchanges {
    Test("ffiq.events.test.exchange"),
    Pandia("ffiq.events.pandia.exchange"),
    Wmp("ffiq.events.wmp.exchange"),
    Tams("ffiq.events.tams.exchange"),
    RetryServicePandia("ffiq.events.rabbitRetryService.pandia.exchange"),
    RetryServiceBevo("ffiq.events.rabbitRetryService.bevo.exchange");

    private String name;

    private Exchanges(final String name) {
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
