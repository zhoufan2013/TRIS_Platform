package com.ai.tris.server.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * Cache Factory.
 * <p/>
 * Created by Sam on 2015/6/7.
 */
public class CacheFactory {

    /**
     * Store cache data.
     */
    private static Map<String, Map<String, Object>> CACHE = new HashMap<String, Map<String, Object>>();

    private static Set<Class<?>> CACHE_IMPL_SET = new HashSet<Class<?>>();

    private static ReentrantLock readWriteLock = new ReentrantLock();

    private static transient Logger logger = LoggerFactory.getLogger(CacheFactory.class);

    /**
     * Get cached map.
     *
     * @param cacheItem cache item key
     * @return cached map value
     */
    public static Map<String, Object> getCacheData(String cacheItem) {
        return null;
    }

    /**
     * Get cache data. Using ReentrantLock is used for for synchronization control.
     *
     * @param cacheItem cache item key
     * @param cacheKey  cache key
     * @return cached data.
     */
    public static Object getCacheData(String cacheItem, String cacheKey) {
        readWriteLock.lock();
        Object cachedData = null;
        Map<String, Object> cachedItem = CACHE.get(cacheItem);
        if (null != cachedItem) {
            cachedData = cachedItem.get(cacheKey);
        }
        readWriteLock.unlock();
        return cachedData;
    }

    /**
     * Load all data to cache.
     */
    public static void loadAllData() {
        readWriteLock.lock();
        readCacheImpl();
        if (CACHE_IMPL_SET.size() > 0) {
            for (Class cacheImpl : CACHE_IMPL_SET) {
                try {
                    ICache iCache = (ICache) (cacheImpl.newInstance());
                    CACHE.put(iCache.getClass().getName(), iCache.loadData());
                } catch (InstantiationException ie) {
                    logger.error(String.format("cache %s load failed.", cacheImpl.getClass().getName()), ie);
                } catch (IllegalAccessException iae) {
                    logger.error(String.format("cache %s load failed.", cacheImpl.getClass().getName()), iae);
                }
            }
        }
        readWriteLock.unlock();
    }

    public static void reloadAll() {
    }

    public static void reload(String cacheItem) {
    }


    private static void readCacheImpl() {
        try {
            Properties prop = new Properties();
            prop.load(CacheFactory.class.getClassLoader().getResourceAsStream("server.properties"));
            String cacheClasses = prop.getProperty("cache.classes");
            if (StringUtils.isEmpty(cacheClasses)) {
                if (logger.isInfoEnabled()) {
                    logger.info("No cache to load.");
                }
                return;
            }
            String[] cachePieces = cacheClasses.split(Pattern.quote(","));
            for (String cacheClassName : cachePieces) {
                Class cacheClass = Class.forName(cacheClassName.trim());
                Class[] interfaces = cacheClass.getInterfaces();
                for (Class _interface : interfaces) {
                    if (_interface.getName().equals(ICache.class.getName())) {
                        CACHE_IMPL_SET.add(cacheClass);
                    }
                }
            }
        } catch (IOException ioe) {
            logger.error("Load server.properties failed.", ioe);
            throw new RuntimeException("Load server.properties failed.");
        } catch (ClassNotFoundException cnfe) {
            logger.error("Class not found exception.", cnfe);
            throw new RuntimeException("Class not found exception.");
        }

    }

    static {
        loadAllData();
    }
}
