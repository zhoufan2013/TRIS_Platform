package com.ai.tris.server.security.exception;

import com.ai.tris.server.security.ResponseBuilder;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Unauthorized Exception.
 * <p/>
 * Created by Sam on 2015/6/10.
 */
public class UnauthorizedException extends WebApplicationException {

    /**
     * Build Unauthorized Exception using passed message.
     *
     * @param msg message defined by caller.
     */
    public UnauthorizedException(String msg) {
        super(Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON_TYPE).entity(
                new ResponseBuilder().buildRspDocument(
                        Response.Status.UNAUTHORIZED.getStatusCode(),
                        msg, 1).toJson()
        ).build());
    }

    /**
     * Build Unauthorized Exception using passed message.
     *
     * @param msg          message defined by caller.
     * @param internalCode internal status code
     */
    public UnauthorizedException(String msg, int internalCode) {
        super(Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON_TYPE).entity(
                        new ResponseBuilder().buildRspDocument(
                                internalCode, msg, 1).toJson()).build()
        );
    }
}
