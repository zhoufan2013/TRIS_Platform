package com.ai.tris.server.example;


import org.apache.commons.lang3.StringUtils;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

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

    @Test
    public void testZhuli() throws Exception{
        Client temp = ClientBuilder.newClient();
        for(int i=0; i< 1000; i++) {
            String result =
                    temp.target("http://jifenxiang.0527life.com/plugin.php?id=tom_weixin_zl&act_id=2&zlkey=100&from=timeline&isappinstalled=0").request().get(String.class);
            int index = StringUtils.indexOf(result, "formhash=", 1000);
            String shareUrl = result.substring(index-93, index + 8 + 1 + 8);
            System.out.println(i + ": try to open share url [" + shareUrl + "]");
            Response shareResponse = temp.target(shareUrl).request().get();
            if(null != shareResponse) {
                System.out.println(i + ": status " + shareResponse.getStatus());
            }
            Thread.sleep(1000);
        }
    }
}
