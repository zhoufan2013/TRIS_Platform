package com.ai.tris.server.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ext.ContextResolver;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Test App
 * Created by Sam on 2015/6/2.
 */
public class App {
    public static String STR_URI = "http://0.0.0.0";
    public static URI BASE_URI = URI.create("http://0.0.0.0:8080/");
    public static final String ROOT_PATH = "helloworld";
    private static transient Logger logger = LoggerFactory.getLogger(App.class);
    public static HttpServer server;

    public static void main(String[] args) {
        try {
            if (null == args || args.length < 1) {
                logger.error("Invalid input parameters, 'PORT' must be passed in.");
                throw new RuntimeException("Exit 0");
            }
            if (!args[0].matches("\\d+")) {
                logger.error("Invalid input parameters, 'PORT' must be a integer");
                throw new RuntimeException("Exit 0");
            }
            BASE_URI = URI.create(STR_URI + ":" + args[0] + "/api");
            System.out.println("\"Hello World\" Jersey Example App");
            final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
            resourceConfig.setApplicationName("HelloWorld");
            // how to bind another resource
            //resourceConfig.registerClasses(AdminResource.class, TrisPermissionCheckResource.class);
/*            .packages(CategoryResource.class
                    .register(LoggingFilter.class)
            .register(createMoxyJsonResolver());*/

            resourceConfig.packages(true, "com.ai.tris.server.resource")
                    .register(LoggingFilter.class).register(createMoxyJsonResolver());

            server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
/*            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...",
                    BASE_URI, ROOT_PATH));
            int result = System.in.read();
            if(logger.isInfoEnabled()) {
                logger.info(String.format("Exit code: %d", result));
            }
            server.shutdownNow();*/
        } catch (Exception ex) {
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

    public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }
}
