package com.ai.tris.server.cache;

import java.util.Map;

/**
 * Cache manager interface.
 * <p/>
 * Created by Sam on 2015/6/7.
 */
public interface ICache {

    Map<String, Object> loadData();
}
