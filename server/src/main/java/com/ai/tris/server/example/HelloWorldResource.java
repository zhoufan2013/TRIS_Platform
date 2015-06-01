package com.ai.tris.server.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Hello World Example
 * Created by Sam on 2015/6/2.
 */
@Path("helloworld")
public class HelloWorldResource {

    public static final String CLICHED_MESSAGE = "Hello World!";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return CLICHED_MESSAGE;
    }

}
