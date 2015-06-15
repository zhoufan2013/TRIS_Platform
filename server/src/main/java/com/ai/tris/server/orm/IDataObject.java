package com.ai.tris.server.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Created by Sam on 2015/6/12.
 */
public interface IDataObject {

    IDataObject achieveData(ResultSet resultSet) throws SQLException;

}
