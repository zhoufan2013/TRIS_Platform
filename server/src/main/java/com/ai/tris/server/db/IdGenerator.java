package com.ai.tris.server.db;

/**
 * Id generator uniform behavior interface definition. At present there is only one interface.
 * <p/>
 * Created by Sam on 2015/6/15.
 */
public interface IdGenerator {
    /**
     * Gets a new Id. If the id pool is not consumed out, return a minimum
     * value based on current cursor. If pool is used out, fetch a new range
     * of ids from db, cache it and return a minimum value.
     *
     * @return new id
     */
    long getNewId();
}
