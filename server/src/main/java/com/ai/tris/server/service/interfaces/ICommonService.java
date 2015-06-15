package com.ai.tris.server.service.interfaces;

import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Test annotation transactional
 * Created by Sam on 2015/6/9.
 */
@Transactional(readOnly=true)
public interface ICommonService {

    void printCallStack();

    List<ClientAppInfoBean> getAllClientAppInfo();

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    void insertSomething(long id);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    void insertSomethingElse(long id);

}
