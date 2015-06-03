package com.ai.tris.server;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Manager Tris resource.
 *
 * Created by Sam on 2015/6/2.
 */
public class TrisResourceManager {

    /**
     * Resource Config
     */
    private static ResourceConfig RES_CONF;

    /**
     * Resource List. What's in it can be reflected to class instance.
     */
    private static Set<String> RES_LIST = new HashSet<String>();


    private static void load() {

    }

    static {
        load();
    }
}
