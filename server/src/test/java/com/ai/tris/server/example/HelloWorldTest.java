package com.ai.tris.server.example;


import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Hello World Resource Test
 * <p/>
 * Created by Sam on 2015/6/2.
 */
public class HelloWorldTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = App.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(App.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Hello World!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("helloworld").request().get(String.class);
        Assert.assertEquals("Hello World!", responseMsg);
    }
}
