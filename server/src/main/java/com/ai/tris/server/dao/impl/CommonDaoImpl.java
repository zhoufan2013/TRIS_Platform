package com.ai.tris.server.dao.impl;

import com.ai.tris.server.dao.interfaces.ICommonDao;
import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Common Database Access Object implementation. It is still in the initial coding stage,
 * a lot of code is not perfect. I believe that after two weeks, everything will be better.
 * <p/>
 * Created by Sam on 2015/6/9.
 */
public class CommonDaoImpl implements ICommonDao {

    /**
     * Log4j implementation. If you want to know more about the log, please
     * visit the official website for more information.
     */
    private transient static Log log = LogFactory.getLog(CommonDaoImpl.class);

    /**
     * Spring jdbc template
     */
    private JdbcTemplate jdbcTemplate;

    public void querySomething() {
        String sql = "select * from client_app_info";
        List<ClientAppInfoBean> clientAppInfoBeans =
                this.jdbcTemplate.query(sql, new RowMapper<ClientAppInfoBean>() {
                    public ClientAppInfoBean mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new ClientAppInfoBean().achieveData(resultSet);
                    }
                });
        //List<Map<String, Object>> result = this.jdbcTemplate.queryForList(sql);
        System.out.println(clientAppInfoBeans);
    }


    public List<ClientAppInfoBean> getTrisClientAppInfo() {
        String sql = "SELECT * FROM client_app_info WHERE expire_date > NOW() AND create_date < NOW() AND state = 'U' ";
        //SqlRowSet sqlRowSet = this.jdbcTemplate.queryForRowSet(sql);=
        //List<ClientAppInfoBean> clientAppInfoBeans =
        return this.jdbcTemplate.query(sql, new RowMapper<ClientAppInfoBean>() {
            public ClientAppInfoBean mapRow(ResultSet resultSet, int i) throws SQLException {
                return new ClientAppInfoBean().achieveData(resultSet);
            }
        });
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
