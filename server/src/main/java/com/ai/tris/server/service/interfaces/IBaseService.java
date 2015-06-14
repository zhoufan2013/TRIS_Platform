package com.ai.tris.server.service.interfaces;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vin on 2015/6/13.
 */
@Transactional(readOnly = true)
public interface IBaseService {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    void insertSomething();
}
