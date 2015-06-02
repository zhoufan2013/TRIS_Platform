package com.ai.tris.server.example;

import org.glassfish.jersey.server.ContainerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by Sam on 2015/6/2.
 */
@Path("check")
public class TrisPermissionCheckResource {

    private static transient Logger log = LoggerFactory.getLogger(TrisPermissionCheckResource.class);

    ContainerRequest request;

    @QueryParam("user")
    String userName;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String check(@QueryParam("user2") String user2) {
        return userName + " is permitted." + user2;
    }

    @GET
    @Path("{userId:\\d+}")
    @Produces("application/json")
    public String check2(@PathParam("userId") String userId) {
        //  request.
        return "check2" + userId + "|" + request.getRequestUri().toString();
    }

    @Context
    public void setRequest(ContainerRequest request) {
        ContainerRequest cr = request;
        log.info(cr.getBaseUri().toString());
        log.info(cr.getRequestUri().toString());
        this.request = request;
    }
}
