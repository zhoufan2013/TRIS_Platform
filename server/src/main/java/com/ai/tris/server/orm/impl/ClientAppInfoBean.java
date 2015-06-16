package com.ai.tris.server.orm.impl;

import com.ai.tris.server.orm.AbstractDataObject;
import com.ai.tris.server.orm.interfaces.IClientAppInfoValue;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * XXX
 * Created by Sam on 2015/6/9.
 */
public class ClientAppInfoBean extends AbstractDataObject implements IClientAppInfoValue {


    public ClientAppInfoBean achieveData(ResultSet resultSet) throws SQLException {
        super.bindData(resultSet);
        return this;
    }

    @Override
    public String toString() {
        return "ClientAppInfoBean{" + "data=" + data + '}';
    }
}
