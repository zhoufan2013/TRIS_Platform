package com.ai.tris.server.orm.impl;

import com.ai.tris.server.db.utils.IdGenUtil;
import com.ai.tris.server.orm.AbstractDataObject;
import com.ai.tris.server.orm.interfaces.ISysSequencesValue;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Object bean mapping to table tris.sys_sequences
 * <p/>
 * Created by Sam on 2015/6/16.
 */
public class SysSequencesBean extends AbstractDataObject implements ISysSequencesValue {

    @Override
    public SysSequencesBean achieveData(ResultSet resultSet) throws SQLException {
        super.bindData(resultSet);
        return this;
    }

    @Override
    public long getNewId() {
        return IdGenUtil.getNewId(S_TABLE_NAME);
    }

    @Override
    public String toString() {
        return S_TABLE_NAME + " {" + "data=" + data + '}';
    }
}