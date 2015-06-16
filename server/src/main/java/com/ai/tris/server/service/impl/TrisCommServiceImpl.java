package com.ai.tris.server.service.impl;

import com.ai.tris.server.dao.interfaces.ITrisCommDao;
import com.ai.tris.server.orm.impl.SysSequencesBean;
import com.ai.tris.server.service.interfaces.ITrisCommService;

import java.util.List;

/**
 * Tris common service implementation.
 * <p/>
 * Created by Sam on 2015/6/16.
 */
public class TrisCommServiceImpl implements ITrisCommService {


    private ITrisCommDao trisCommDao;

    @Override
    public List<SysSequencesBean> getSysSequences() {
        return trisCommDao.getSysSequences();
    }

    @Override
    public SysSequencesBean getAndUpdateSequence(String seqName) {
        return trisCommDao.lockAndUpdateSysSeq(seqName);
    }

    public void setTrisCommDao(ITrisCommDao trisCommDao) {
        this.trisCommDao = trisCommDao;
    }
}
