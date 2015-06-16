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
 * abstract data object
 * Created by Sa, on 2015/6/9.
 */
public abstract class AbstractDataObject implements IDataObject {

    private transient static Log log = LogFactory.getLog(AbstractDataObject.class);
    protected Set<String> colNames = new HashSet<>();
    protected Map<String, Object> data = new HashMap<>();

    /**
     * Get all column names.
     *
     * @param resultSet result set
     */
    protected void buildColumnNames(ResultSet resultSet) throws SQLException {
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

    protected void bindData(ResultSet resultSet) throws SQLException {
        buildColumnNames(resultSet);
        if (checkColNameSet()) {
            data.clear();
            for (String colName : colNames) {
                data.put(colName.toUpperCase(), resultSet.getObject(colName));
            }
        }
    }

    public Object get(String key) {
        return data.get(key);
    }

    @Override
    public void set(String key, Object object) {
        data.put(key, object);
    }

    @Override
    public Long getLong(String key) {
        String object = String.valueOf(data.get(key));
        if (StringUtils.isNotEmpty(object) && object.matches("\\d+")) {
            return Long.valueOf(object);
        }
        return null;
    }

    @Override
    public Integer getInt(String key) {
        String object = String.valueOf(data.get(key));
        if (StringUtils.isNotEmpty(object) && object.matches("\\d+")) {
            return Integer.valueOf(object);
        }
        return null;
    }

    public String getString(String key) {
        return String.valueOf(data.get(key));
    }

    private boolean checkColNameSet() {
        return (null != colNames && colNames.size() > 0);
    }
}
