package com.sf.channel;

import com.sf.chronicle.queue.PersistenceSpace;
import com.sf.chronicle.queue.PersistenceSpaces;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityasofat on 22/01/2016.
 */
public class ChannelsTest {

    private PersistenceSpace persistenceSpace;

    @Before
    public void setup() {
        persistenceSpace = PersistenceSpaces.defaultPersistenceSpace();
    }

    @After
    public void tearDown() {
        persistenceSpace.removePersistenceSpace();
    }

    @Test
    public void createAChannel() {
        //Given
        String channelName = "reader";
        List<String> collector = new ArrayList<String>();
        //When
        Channel channel = Channels.newStringChannel(channelName, collector, persistenceSpace);
        channel.close();
        //Then
        MatcherAssert.assertThat(channel.getName(), CoreMatchers.equalTo(channelName));
    }



}