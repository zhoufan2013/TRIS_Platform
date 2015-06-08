package com.ai.tris.server.db.mongodb;

/**
 * specify database const info.
 *
 * Created by Sam on 2015/6/8.
 */
public class DbConstInfo {
    public interface DbName {
        // base database.
        String base = "tris_base";
    }

    public interface Collection {
        String clientAppInfo = "client_app_info";
    }
}
