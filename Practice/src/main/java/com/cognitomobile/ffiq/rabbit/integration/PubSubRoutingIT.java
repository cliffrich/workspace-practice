package com.cognitomobile.ffiq.rabbit.integration;

import com.cognitomobile.ffiq.rabbit.integration.domain.EventA;
import com.cognitomobile.ffiq.rabbit.integration.domain.EventB;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author davidy
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/rabbit-integration.xml"})
@DirtiesContext // this test rabbit configuration is incompatible with RabbitRetryServiceWithBasicPublishIT
public class PubSubRoutingIT {

    @Autowired
    QueuePurger queuePurger;

    @Autowired
    @Qualifier("dlqName")
    String dlqName;

    @Autowired
    MessageChannel sendChannelTest;

    @Autowired
    MessageReceiver receiver;

    @Autowired
    MessageReceiver receiverCog;

    @Autowired
    MessageSupport messageSupport;

    @Before
    public void setup() {
        receiver.clear();
        //queuePurger.clearQueue(dlqName);
    }

    public void sendMsg(final String tenant, final Object o) {
        Message msg = messageSupport.buildMessage(tenant, o, MessageSource.ANY);
        sendChannelTest.send(msg);
    }

    @Test
    public void sendReceiveTest() throws Exception {
//        for (int i = 1; i <= 3; i++) {
//            sendMsg("bevo", new EventA(i, "bevo " + new Date()));
//        }
//        for (int i = 1; i <= 3; i++) {
//            sendMsg("cog", new EventA(i, "cog " + new Date()));
//        }

        for (int i = 1; i <= 3; i++) {
            sendMsg("fred", new EventA(i, "fred " + new Date()));
        }

        Thread.sleep(2 * 1000);

        Assert.assertEquals(6, receiver.size());
        Assert.assertEquals(3, receiverCog.size());

    }

//    @Test
    public void sendRetryTest() throws Exception {

        receiver.setAction(1);
        sendMsg("bevo", new EventA(1, "bevo " + new Date()));


        Thread.sleep(2 * 1000);

        Assert.assertEquals(0, receiver.size());
        Assert.assertEquals(3, receiver.getCount());
        //TODO should check DLQ
    }

//    @Test
    public void sendInvalidMsgTest() throws Exception {
        receiver.setAction(2);
        sendMsg("bevo", new EventA(1, "bevo " + new Date()));


        Thread.sleep(2 * 1000);

        Assert.assertEquals(0, receiver.size());
        Assert.assertEquals(1, receiver.getCount());

        //TODO should check DLQ
    }

//    @Test
    public void sendReceiveAandBTest() throws Exception {
        for (int i = 1; i <= 3; i++) {
            sendMsg("bevo", new EventA(i, "bevo " + new Date()));
        }
        for (int i = 1; i <= 3; i++) {
            sendMsg("bevo", new EventB(i));
        }

        Thread.sleep(2 * 1000);

        Assert.assertEquals(6, receiver.size());

    }

//    @Test
    public void sendReceiveAandBTestNoBsOnFred() throws Exception {
        for (int i = 1; i <= 3; i++) {
            sendMsg("fred", new EventA(i, "fred " + new Date()));
        }
        for (int i = 1; i <= 3; i++) {
            sendMsg("fred", new EventB(i));
        }

        Thread.sleep(2 * 1000);

        Assert.assertEquals(3, receiver.size());

    }

}

