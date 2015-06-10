package com.ai.tris.server.security.exception.mapper;

import com.ai.tris.server.security.ResponseBuilder;
import org.bson.json.JsonParseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Json parse exception provider
 * Created by Sam on 2015/6/10.
 */
@Provider
public class BsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

    public Response toResponse(JsonParseException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON_TYPE).entity(
                new ResponseBuilder().buildRspDocument(
                        Response.Status.UNAUTHORIZED.getStatusCode(),
                        String.format("Failed to parse json content: %s", exception.getMessage()),
                        1).toJson()
        ).build();
    }
}
