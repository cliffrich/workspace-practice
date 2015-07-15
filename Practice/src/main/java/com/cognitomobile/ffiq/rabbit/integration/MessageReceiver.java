package com.cognitomobile.ffiq.rabbit.integration;

import com.cognitomobile.ffiq.rabbit.integration.domain.EventA;
import com.cognitomobile.ffiq.rabbit.integration.domain.EventB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * @author davidy
 */
public class MessageReceiver {
    private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);


    //private final static HashMap<Integer,EventA> received =  new HashMap<>();
    private List<Object> received = new ArrayList<Object>();

    private int action = 0;  //0 = accept, 1 = transient exception, 2 = failed exception;
    private int count = 0;

    //
    public void receive(@Header("TENANT") final String tenant, final EventA msg) {
        System.out.println(this + " received EventA " + msg.toString() + " on tenant " + tenant);
        count++;

        if (action == 1) {
            throw new NotReadyForMessageException("An exception");
        } else if (action == 2) {
            throw new UnsupportedMessageException("Invalid message");
        }
        received.add(msg);
    }

    public void receive(final EventB msg) {
        System.out.println(this + " received EventB " + msg.toString());
        count++;

        if (action == 1) {
            throw new NotReadyForMessageException("An exception");
        } else if (action == 2) {
            throw new UnsupportedMessageException("Invalid message");
        }
        received.add(msg);
    }

    public void receive(final Object o) {
        System.out.println(this + " received Object " + o.toString());
    }

    public int getAction() {
        return action;
    }

    public void setAction(final int action) {
        this.action = action;
    }

    public int getCount() {
        return count;
    }

    public int size() {
        return received.size();
    }

    public void clear() {
        received.clear();
        action = 0;
        count = 0;
    }

}
