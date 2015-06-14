package com.ai.tris.server.service.impl;

import com.ai.tris.server.dao.interfaces.IBaseDao;
import com.ai.tris.server.service.interfaces.IBaseService;

/**
 * 111
 * Created by Sam on 2015/6/13.
 */
public class BaseServiceImpl implements IBaseService {

    private IBaseDao baseDao;

    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void insertSomething() {
        baseDao.insertBaseData();
    }
}
