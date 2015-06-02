package com.ai.tris.server.resource;

import com.ai.tris.server.example.App;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * admin controller.
 * 1. shutdown process gracefully.
 * <p/>
 * Created by Sam on 2015/6/2.
 */
@Path("admin00")
public class AdminResource {

    /**
     * Shutdown process gracefully.
     *
     * @return exit code
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{command}")
    public String shutdownNow(@PathParam("command") String command) {
        if (StringUtils.isEmpty(command) || !StringUtils.equals("shutdown123", command)) {
            return "unknown command!";
        }

        if (null != App.server && App.server.isStarted()) {
            App.server.shutdownNow();
            Runtime.getRuntime().exit(0);
        } else {
            return "server has been shutdown.";
        }
        return "Server is shutting down, re-command to check.";
    }
}
