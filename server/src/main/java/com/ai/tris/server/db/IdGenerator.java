package com.ai.tris.server.db;

/**
 *
 * Created by Sam on 2015/6/15.
 */
public interface IdGenerator {
    long getNewId(String tableName);
}
