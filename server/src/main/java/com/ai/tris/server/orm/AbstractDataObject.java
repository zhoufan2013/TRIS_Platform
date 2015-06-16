package com.ai.tris.server.orm;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Abstract the public property of database beans.
 * <p/>
 * Created by Sam, on 2015/6/9.
 */
public abstract class AbstractDataObject implements IDataObject {

    private transient static Log log = LogFactory.getLog(AbstractDataObject.class);

    /**
     * Put table column names into this <code>Set</code>
     */
    protected Set<String> colNames = new HashSet<>();

    /**
     * Store db data.
     */
    protected Map<String, Object> data = new HashMap<>();

    /**
     * Get all column names, and put them into <code>colNames</code>.
     *
     * @param resultSet result set
     */
    protected void buildColNamesSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        if (null != metaData) {
            int columnCount = metaData.getColumnCount();
            if (columnCount > 0) {
                for (int i = 1; i <= columnCount; ) {
                    colNames.add(metaData.getColumnName(i++));
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug(String.format("Dynamic column set [%s]", colNames.toString()));
        }
    }

    /**
     * Read db data out from result set, and store into data map.
     *
     * @param resultSet database result
     * @throws SQLException
     */
    protected void bindData(ResultSet resultSet) throws SQLException {
        buildColNamesSet(resultSet);
        if (checkColNameSet()) {
            data.clear();
            for (String colName : colNames) {
                data.put(colName.toUpperCase(), resultSet.getObject(colName));
            }
        }
    }

    /**
     * Implementation of {@link IDataObject}
     *
     * @param key value key mapping to database column.
     * @return object value
     */
    public Object get(String key) {
        return data.get(key);
    }


    @Override
    public void set(String key, Object object) {
        data.put(key, object);
    }

    /**
     * If the value get out from data map is null or is not long value
     * then return null
     *
     * @param key value key
     * @return long value
     */
    @Override
    public Long getLong(String key) {
        String object = String.valueOf(get(key));
        if (StringUtils.isNotEmpty(object) && object.matches("\\d+")) {
            return Long.valueOf(object);
        }
        return null;
    }

    @Override
    public Integer getInt(String key) {
        String object = String.valueOf(get(key));
        if (StringUtils.isNotEmpty(object) && object.matches("\\d+")) {
            return Integer.valueOf(object);
        }
        return null;
    }

    public String getString(String key) {
        return String.valueOf(get(key));
    }

    @Override
    public long getNewId() {
        throw new UnsupportedOperationException("Get new id should be implemented.");
    }

    /**
     * In some situation, this method is not needed. -_-
     *
     * @return true or false
     */
    private boolean checkColNameSet() {
        return (null != colNames && colNames.size() > 0);
    }
}
