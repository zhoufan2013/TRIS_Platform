package com.ai.tris.server.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

/**
 * Test App
 * Created by Sam on 2015/6/2.
 */
public class App {
    public static final URI BASE_URI = URI.create("http://localhost:8080/");
    public static final String ROOT_PATH = "helloworld";
    private static transient Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            System.out.println("\"Hello World\" Jersey Example App");


            final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
            resourceConfig.setApplicationName("HelloWorld");
            // how to bind another resource
            resourceConfig.registerClasses(TrisPermissionCheckResource.class);

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...",
                    BASE_URI, ROOT_PATH));
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            logger.error(null, ex);
        }
    }

    public static HttpServer startServer() {
        if (logger.isErrorEnabled()) {
            logger.error(String.format("Starting %s", "Server"));
        }
        final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
    }
}
