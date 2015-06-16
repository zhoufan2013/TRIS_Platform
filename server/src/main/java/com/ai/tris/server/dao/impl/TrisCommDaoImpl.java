package com.ai.tris.server.dao.impl;

import com.ai.tris.server.dao.interfaces.ITrisCommDao;
import com.ai.tris.server.orm.impl.SysSequencesBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sam on 2015/6/16.
 */
public class TrisCommDaoImpl implements ITrisCommDao {


    private JdbcTemplate trisJdbcTemplate;

    @Override
    public List<SysSequencesBean> getSysSequences() {
        String sql = "select * from sys_sequences";
        return trisJdbcTemplate.query(sql, new RowMapper<SysSequencesBean>() {
            @Override
            public SysSequencesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SysSequencesBean().achieveData(rs);
            }
        });
    }

    @Override
    public SysSequencesBean lockAndUpdateSysSeq(String seqName) {
        String selectSql = "select * from sys_sequences where sequence_name = ? for update";
        String updateSql = "update sys_sequences set last_number = ? where sequence_name = ?";
        SysSequencesBean sysSequencesBean =
                trisJdbcTemplate.queryForObject(selectSql, new Object[]{seqName}, new RowMapper<SysSequencesBean>() {
                    @Override
                    public SysSequencesBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new SysSequencesBean().achieveData(rs);
                    }
                });
        if (null == sysSequencesBean) {
            return null;
        }
        long newLastNumber = sysSequencesBean.getLong(SysSequencesBean.S_LAST_NUMBER)
                + sysSequencesBean.getLong(SysSequencesBean.S_JVM_STEP_BY);
        trisJdbcTemplate.update(updateSql, newLastNumber, seqName);
        return sysSequencesBean;
    }

    public void setTrisDataSource(DataSource dataSource) {
        this.trisJdbcTemplate = new JdbcTemplate(dataSource);
    }
}
