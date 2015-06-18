package com.ai.tris.server.security;


import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * For security aspect, build this filter to perform the token validation.
 * <p/>
 * Created by Sam on 2015/6/7.
 */
@Provider
public class SecurityTokenFilter implements ContainerRequestFilter {


    public void filter(ContainerRequestContext requestContext) throws IOException {

/*        ContainerRequest request = (ContainerRequest) requestContext;
        MultivaluedMap<String, String> headers = request.getHeaders();
        if (!headers.containsKey("token")) {
            Response response = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ResponseBuilder().buildRspDocument(
                            Response.Status.UNAUTHORIZED.getStatusCode(),
                            Response.Status.UNAUTHORIZED.getReasonPhrase(), 1).toJson())
                    .type(MediaType.APPLICATION_JSON_TYPE).build();
            throw new WebApplicationException(response);
        }
        System.out.println(headers);*/
    }

}
