package com.ai.tris.server.db;


/**
 * Abstract id generator,
 *
 * Created by Sam on 2015/6/15.
 */
public abstract class AbstractIdGenerator implements IdGenerator{
    private long lastId;
    private int stepLength;

    private String tableName;


    @Override
    public long getNewId(String tableName) {
        return 0;
    }

    protected abstract void resetLastId();
}
