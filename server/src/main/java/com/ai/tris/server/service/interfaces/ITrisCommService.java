package com.ai.tris.server.service.interfaces;

import com.ai.tris.server.orm.impl.SysSequencesBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * abstract common service.
 * <p/>
 * Created by Vin on 2015/6/16.
 */
@Transactional(readOnly = true)
public interface ITrisCommService {

    List<SysSequencesBean> getSysSequences();

    @Transactional(readOnly = false)
    SysSequencesBean getAndUpdateSequence(String seqName);
}
