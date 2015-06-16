package com.ai.tris.server.dao.interfaces;

import com.ai.tris.server.orm.impl.SysSequencesBean;

import java.util.List;

/**
 * Tris db accessor.
 * Created by Sam on 2015/6/16.
 */
public interface ITrisCommDao {

    List<SysSequencesBean> getSysSequences();

    SysSequencesBean lockAndUpdateSysSeq(String seqName);

}
