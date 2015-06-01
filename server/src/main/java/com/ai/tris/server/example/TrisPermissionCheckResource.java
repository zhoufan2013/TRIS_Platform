package com.ai.tris.server.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by Sam on 2015/6/2.
 */
@Path("check")
public class TrisPermissionCheckResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String check() {
        return "true";
    }
}
