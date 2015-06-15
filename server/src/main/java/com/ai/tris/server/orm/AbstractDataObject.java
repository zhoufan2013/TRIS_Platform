package com.ai.tris.server.orm;

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
 * abstract datta object
 * Created by Sa, on 2015/6/9.
 */
public abstract class AbstractDataObject implements IDataObject {

    private transient static Log log = LogFactory.getLog(AbstractDataObject.class);
    protected Set<String> colNames = new HashSet<String>();
    protected Map<String, Object> data = new HashMap<String, Object>();

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

    private boolean checkColNameSet() {
        return (null != colNames && colNames.size() > 0);
    }
}
