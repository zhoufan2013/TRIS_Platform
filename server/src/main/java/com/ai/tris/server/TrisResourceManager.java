package com.ai.tris.server;

import com.ai.tris.server.security.exception.mapper.BsonParseExceptionMapper;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Manager Tris resource.
 * <p/>
 * Created by Sam on 2015/6/2.
 */
public class TrisResourceManager {

    /**
     * Resource Config
     */
    public static ResourceConfig RES_CONF;

    /**
     * Resource List. What's in it can be reflected to class instance.
     */
    private static Set<String> RES_LIST = new HashSet<String>();

    static {
        try {
            load();
        } catch (IOException ioe) {
            throw new RuntimeException("Load resource from file (resource.properties) failed.");
        }
    }

    public static void registerExceptionMapper(Properties properties) {
        RES_CONF.register(BsonParseExceptionMapper.class);
    }

    private static void load() throws IOException {
        Properties prop = new Properties();
        prop.load(TrisResourceManager.class.getClassLoader().getResourceAsStream("resource.properties"));
        String resPackage = prop.getProperty("resource.packages");
        if (StringUtils.isNotEmpty(resPackage)) {
            RES_CONF = new ResourceConfig();
            RES_CONF.setApplicationName("Tris-Server");
            RES_CONF.packages(Boolean.TRUE, resPackage.split(Pattern.quote(",")));
        }
        // register exception mapper
        registerExceptionMapper(prop);
    }
}
