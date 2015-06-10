package com.ai.tris.server.resource;

import com.ai.tris.server.example.App;
import com.ai.tris.server.resource.produce.Category;
import com.ai.tris.server.resource.produce.Group;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Created by Vin on 2015/6/3.
 */
public class CategoryResourceTest {
    private static transient Log logger = LogFactory.getLog(CategoryResourceTest.class);
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
    public void testCategory() {
        String responseMsg = target.path("category").request().get(String.class);
        logger.error(responseMsg);
        //Assert.assertEquals("Hello World!", responseMsg);
    }

    @Test
    public void testPostCategory() {
        Category category = new Category();
        category.setCategory("InputCategory");
        category.addToGroup(new Group("group1"));
        Entity entity = Entity.json(category);
        Response response = target.path("category").request(MediaType.APPLICATION_JSON_TYPE).post(entity);
        System.out.println(response.toString());
    }
}
