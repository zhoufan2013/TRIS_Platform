package com.ai.tris.server.db.mysql;

import com.ai.tris.server.AppContextFactory;
import com.ai.tris.server.db.AbstractIdGenerator;
import com.ai.tris.server.orm.impl.SysSequencesBean;
import com.ai.tris.server.service.interfaces.ITrisCommService;

/**
 * Mysql id generator
 * <p/>
 * Created by Sam on 2015/6/15.
 */
public class MysqlIdGenerator extends AbstractIdGenerator {

    public MysqlIdGenerator(long lastNumber, String seqName, int stepLength) {
        super(lastNumber, seqName, stepLength);
    }

    @Override
    protected void resetLastId() {
        ITrisCommService trisCommService =
                AppContextFactory.getAppContext().getBean(ITrisCommService.class.getName(), ITrisCommService.class);
        SysSequencesBean nSysSeq = trisCommService.getAndUpdateSequence(sequenceName);
        updateCursor(nSysSeq.getLong(SysSequencesBean.S_LAST_NUMBER), nSysSeq.getInt(SysSequencesBean.S_JVM_STEP_BY));
    }
}
