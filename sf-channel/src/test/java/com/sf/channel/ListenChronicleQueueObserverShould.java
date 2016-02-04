package com.sf.channel;

import com.sf.chronicle.queue.ChronicleQueue;
import com.sf.chronicle.queue.ChronicleQueues;
import com.sf.chronicle.queue.MessageUtil;
import com.sf.chronicle.queue.PersistenceSpaces;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.sf.chronicle.queue.PersistenceSpaces.DEFAULT;

/**
 * Created by adityasofat on 03/02/2016.
 */
public class ListenChronicleQueueObserverShould {

    @Test
    public void collectMessages() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<String> messageCollector = new ArrayList<String>();
        Adaptor<String> stringAdaptor = new  Adaptor<String>() {
            @Override
            public void process(String  message) {
                messageCollector.add(message);
                System.out.println(Thread.currentThread().getName() + "Adaptor Received message [" + message + "]");
                if ( messageCollector.size() == MessageUtil.getMessages().size()){
                    countDownLatch.countDown();
                }
            }
        };
        String channelName = "observedChannel";
        ChronicleQueue<String> stringChronicleQueue = ChronicleQueues.<String>newObservableQueue(channelName, PersistenceSpaces.persistenceSpace(DEFAULT));
        ListenChronicleQueueObserver<String> stringListenChronicleQueueObserver = new ListenChronicleQueueObserver<String>(stringChronicleQueue, -1, stringAdaptor);
        stringListenChronicleQueueObserver.init();
        stringChronicleQueue.publishMessages(MessageUtil.getMessages(),128);
        countDownLatch.await();
        MatcherAssert.assertThat(messageCollector, Matchers.containsInAnyOrder(MessageUtil.getMessagePayLoad().toArray()));
    }

}