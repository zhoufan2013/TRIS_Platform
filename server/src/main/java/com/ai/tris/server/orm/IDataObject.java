package com.ai.tris.server.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Define the integration of business objects.
 * <p/>
 * Created by Sam on 2015/6/12.
 */
public interface IDataObject {

    /**
     * Convert data from <code>ResultSet</code> to bean.
     *
     * @param resultSet query result
     * @return Data bean
     * @throws SQLException
     */
    IDataObject achieveData(ResultSet resultSet) throws SQLException;

    /**
     * Put a key-value pair to data bean.
     *
     * @param key    key of value, type of String
     * @param object value
     */
    void set(String key, Object object);

    /**
     * Get something from data bean with <code>Object</code> type.
     *
     * @param key value key mapping to database column.
     * @return Object value
     */
    Object get(String key);

    /**
     * Convert object value to String.
     *
     * @param key object key
     * @return the String value of object
     */
    String getString(String key);

    /**
     * Convert object value to Long
     *
     * @param key value key
     * @return Long value
     */
    Long getLong(String key);

    /**
     * Convert object value to Integer.
     *
     * @param key object key
     * @return Integer value
     */
    Integer getInt(String key);

    /**
     * Get new number type primary key.
     *
     * @return new id
     */
    long getNewId();
}
