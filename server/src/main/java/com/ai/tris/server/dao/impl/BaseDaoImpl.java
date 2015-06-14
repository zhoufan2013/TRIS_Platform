package com.ai.tris.server.dao.impl;

import com.ai.tris.server.dao.interfaces.IBaseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 1111
 * Created by Sam on 2015/6/13.
 */
public class BaseDaoImpl implements IBaseDao {

    private transient static Log log = LogFactory.getLog(BaseDaoImpl.class);

    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBaseData() {
        int result = jdbcTemplate.update("insert into tris_static_data (data_code, data_value, data_group, state) " +
                        "values (?,?,?,?)", "Auth", "tris_web_10", "secret_key_10", "U");
        log.debug(result);
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
