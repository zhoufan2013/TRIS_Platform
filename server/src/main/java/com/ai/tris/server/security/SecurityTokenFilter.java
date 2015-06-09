package com.ai.tris.server.security;

import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
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
        ContainerRequest request = (ContainerRequest) requestContext;
        //System.out.println(request.getPath(false));
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        System.out.println(headers);
        //System.out.println();
    }
}
