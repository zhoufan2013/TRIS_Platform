package com.ai.tris.server.cache;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private static transient Log logger = LogFactory.getLog(CacheFactory.class);

    static {
        loadAllData();
    }

    /**
     * Get cached map. If list kind data is wanted, this method can do
     * such the thing.
     *
     * @param cacheItem cache item key
     * @return cached map value
     */
    public static Map<String, Object> getCacheData(String cacheItem) {
        try {
            readWriteLock.lock();
            Map<String, Object> cachedMap = CACHE.get(cacheItem);
            return (null != cachedMap && cachedMap.size() > 0) ? cachedMap : new HashMap<String, Object>(0);
        } finally {
            readWriteLock.unlock();
        }
    }

    /**
     * Get cache data. Using ReentrantLock is used for for synchronization control.
     *
     * @param cacheItem cache item key
     * @param cacheKey  cache key
     * @return cached data.
     */
    public static Object getCacheData(String cacheItem, String cacheKey) {
        try {
            readWriteLock.lock();
            Map<String, Object> cachedItem = CACHE.get(cacheItem);
            if (null != cachedItem) {
                return cachedItem.get(cacheKey);
            }
            return null;
        } finally {
            readWriteLock.unlock();
        }
    }

    /**
     * Get cache data. Using ReentrantLock is used for for synchronization control.
     *
     * @param cacheItem cache item key
     * @param cacheKey  cache key
     * @return cached data.
     */
    public static String getCacheDataString(String cacheItem, String cacheKey) {
        Object temp;
        return (temp = getCacheData(cacheItem, cacheKey)) == null ? null : String.valueOf(temp);
    }

    /**
     * Load all data to cache.
     */
    public static void loadAllData() {
        try {
            readWriteLock.lock();
            readCacheImpl();
            if (CACHE_IMPL_SET.size() > 0) {
                for (Class cacheImpl : CACHE_IMPL_SET) {
                    try {
                        try {
                            ICache iCache = (ICache) (cacheImpl.newInstance());
                            CACHE.put(iCache.getClass().getName(), iCache.loadData());
                        } catch (InstantiationException ie) {
                            logger.error(String.format("cache %s load failed.", cacheImpl.getClass().getName()), ie);
                        }
                    } catch (IllegalAccessException iae) {
                        logger.error(String.format("cache %s load failed.", cacheImpl.getClass().getName()), iae);
                    }
                }
            }
        } finally {
            readWriteLock.unlock();
        }
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
}
