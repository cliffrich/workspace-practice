package com.cognitomobile.ffiq.rabbit.integration;

import org.apache.commons.lang.StringUtils;

/**
 * @author liz.swallow
 * (c) Cognito Ltd. All rights reserved.
 */
public enum MessageSource {
    INTERNAL("Int"),
    MOBILE("Mob"),
    API("Api"),
    ANY(null),
    TO_360("To_360"),
    FROM_360("From_360"),
    TAMS_WMP_BRIDGE_RESEND("Tams_Wmp_Bridge_Resend");

    private final String key;

    MessageSource(String v) {
        key = v;
    }

    public String key() {
        return key;
    }

    public static MessageSource fromKey(String v) {
        for (MessageSource c: MessageSource.values()) {
            if (!StringUtils.isEmpty(c.key)) {
                if(c.key.equals(v)) {
                    return c;
                }
            }
        }
        throw new IllegalArgumentException(v);
    }
}
