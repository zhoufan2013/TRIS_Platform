package com.ai.tris.server.dao.impl;

import com.ai.tris.server.AppContextFactory;
import com.ai.tris.server.dao.interfaces.ICommonDao;
import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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


    @Override
    public void insertSomething(long id) {
        int result = jdbcTemplate.update("insert into client_app_info(id, app_id, secret_key, state, create_date, expire_date) " +
                "values (?,?,?,?,?,?)", id, "tris_web_10", "secret_key_10", "U", new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis() + 100000000));
        log.debug(result);
    }


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
