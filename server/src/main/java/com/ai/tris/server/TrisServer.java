package com.ai.tris.server;

import com.ai.tris.server.security.SecurityTokenFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

import javax.ws.rs.ext.ContextResolver;
import java.util.*;

/**
 * Tris Server holds server instance, and it is built implementing Jersey.
 * <p/>
 * Created by Sam on 2015/6/2.
 */
public class TrisServer {
    final static String OPTIONS_EXAMPLE = "-port 48000 -bPath api -MoxyJson true -enableLog true";
    final static String[] OPTIONS = new String[]{"-port", "-bPath", "-MoxyJson", "-enableLog"};
    final static String V_URI = "http://0.0.0.0";
    final static String DEFAULT_PORT = "48000";
    public static String BASE_PATH = "/";
    public static HttpServer httpServer;
    /*slf4j log*/
    private static transient Log log = LogFactory.getLog(TrisServer.class);

    /**
     * Start a process.
     *
     * @param args input parameters
     */
    public static void main(String[] args) {
        TrisServer instance = new TrisServer();
        Map<String, String> options = instance.parseMainInput(args);
        String strUri = V_URI;
        strUri += ":";

        if (options.containsKey("-port")
                && StringUtils.isNotEmpty(options.get("-port")) && options.get("-port").matches("\\d+")) {
            strUri += options.get("-port");
        } else {
            if (log.isDebugEnabled()) {
                log.debug(String.format("No port specified, using default %s instead.", DEFAULT_PORT));

            }
            strUri += DEFAULT_PORT;
        }

        if (options.containsKey("-bPath") && StringUtils.isNotEmpty(options.get("-bPath"))) {
            strUri += "/";
            strUri += options.get("-bPath");
            BASE_PATH += options.get("-bPath");
        }
        instance.enableMoxyJson(options);
        instance.enableLoggingFilter(options);

        // register request filter
        TrisResourceManager.RES_CONF.registerClasses(SecurityTokenFilter.class);

        // start http server.
        httpServer = GrizzlyHttpServerFactory.createHttpServer(java.net.URI.create(strUri), TrisResourceManager.RES_CONF);
        if (log.isDebugEnabled()) {
            log.debug(String.format("http server [%s] is running.", strUri));
        }
    }

    ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }

    /**
     * Enable logging filter
     *
     * @param options options
     * @return ..
     */
    boolean enableLoggingFilter(Map<String, String> options) {
        if (options.containsKey("-enableLog")
                && StringUtils.isNotEmpty(options.get("-enableLog"))
                && StringUtils.equalsIgnoreCase("true", options.get("-enableLog"))) {
            if (log.isDebugEnabled()) {
                log.debug("LoggingFilter is Enabled.");
            }
            TrisResourceManager.RES_CONF.register(LoggingFilter.class);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    boolean enableMoxyJson(Map<String, String> options) {
        if (options.containsKey("-MoxyJson")
                && StringUtils.isNotEmpty(options.get("-MoxyJson"))
                && StringUtils.equalsIgnoreCase("true", options.get("-MoxyJson"))) {
            if (log.isDebugEnabled()) {
                log.debug("MoxyJsonResolver is requested to be registered.");
            }
            TrisResourceManager.RES_CONF.register(createMoxyJsonResolver());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Parse input parameters of main function.
     *
     * @param args input params
     * @return param key-value pair
     */
    Map<String, String> parseMainInput(String[] args) {
        Map<String, String> options = new HashMap<String, String>();
        if (validMainInput(args)) {
            for (int i = 0; i < args.length; ) {
                options.put(args[i++], args[i++]);
            }
        }
        validInputOptions(options);
        if (log.isDebugEnabled()) {
            log.debug(String.format("passed-in options: %s", options));
        }
        return options;
    }

    /**
     * Check options' validation
     *
     * @param options options
     * @return Boolean.True or false
     */
    boolean validInputOptions(Map<String, String> options) {
        if (null == options) {
            return Boolean.FALSE;
        }
        Set<String> optionKeys = options.keySet();
        Collection<String> knownOptions = Arrays.asList(OPTIONS);
        for (String optionKey : optionKeys) {
            if (log.isDebugEnabled()) {
                log.debug(String.format("%s = %s", optionKey.substring(1, optionKey.length()), options.get(optionKey)));
            }
            if (!knownOptions.contains(optionKey)) {
                throw new RuntimeException(String.format("unknown option [%s]", optionKey));
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Param args can't be null.
     *
     * @param args params array
     */
    boolean validMainInput(String[] args) {
        if (null == args || args.length == 0 || args.length % 2 != 0) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Wrong input parameters. See the input params: [%s]", OPTIONS_EXAMPLE));
            }
            throw new RuntimeException("Invalid Input Parameters. Please pass in key-value pair(s)");
        }
        return Boolean.TRUE;
    }
}