package com.ai.tris.server.service.impl;

import com.ai.tris.server.dao.interfaces.ICommonDao;
import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import com.ai.tris.server.service.interfaces.ICommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by Sam on 2015/6/9.
 */
public class CommonServiceImpl implements ICommonService {
    private transient static Log log = LogFactory.getLog(CommonServiceImpl.class);

    ICommonDao commonDao;

    public void printCallStack() {
        commonDao.querySomething();
    }


    public List<ClientAppInfoBean> getAllClientAppInfo() {
        return commonDao.getTrisClientAppInfo();
    }

    public void setCommonDao(ICommonDao commonDao) {
        this.commonDao = commonDao;
    }
}
