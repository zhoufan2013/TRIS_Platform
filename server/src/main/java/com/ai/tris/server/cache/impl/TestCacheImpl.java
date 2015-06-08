package com.ai.tris.server.cache.impl;

import com.ai.tris.server.cache.ICache;

import java.util.HashMap;
import java.util.Map;

/**
 * Test Cache
 * Created by Sam on 2015/6/7.
 */
public class TestCacheImpl implements ICache {

    public Map<String, Object> loadData() {
        Map<String, Object> testCache = new HashMap<String, Object>();
        testCache.put("cache_1", "hello world");
        return testCache;
    }
}
