package com.ai.tris.server.orm.impl;

import com.ai.tris.server.db.utils.IdGenUtil;
import com.ai.tris.server.orm.AbstractDataObject;
import com.ai.tris.server.orm.interfaces.IClientAppInfoValue;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Object bean mapping to table tris.client_app_info.
 * <p/>
 * Created by Sam on 2015/6/9.
 */
public class ClientAppInfoBean extends AbstractDataObject implements IClientAppInfoValue {


    public ClientAppInfoBean achieveData(ResultSet resultSet) throws SQLException {
        super.bindData(resultSet);
        return this;
    }

    @Override
    public long getNewId() {
        return IdGenUtil.getNewId(S_TABLE_NAME);
    }

    /**
     * Override method <code>toString</code>
     *
     * @return String
     */
    @Override
    public String toString() {
        return S_TABLE_NAME + " {" + "data=" + data + '}';
    }
}