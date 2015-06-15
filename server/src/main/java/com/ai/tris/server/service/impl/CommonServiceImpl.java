package com.ai.tris.server.service.impl;

import com.ai.tris.server.AppContextFactory;
import com.ai.tris.server.dao.interfaces.IBaseDao;
import com.ai.tris.server.dao.interfaces.ICommonDao;
import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import com.ai.tris.server.service.interfaces.IBaseService;
import com.ai.tris.server.service.interfaces.ICommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 *
 * Created by Sam on 2015/6/9.
 */
public class CommonServiceImpl implements ICommonService {
    private transient static Log log = LogFactory.getLog(CommonServiceImpl.class);

    ICommonDao commonDao;
    IBaseService baseService;

    public void printCallStack() {
        commonDao.querySomething();
    }


    public List<ClientAppInfoBean> getAllClientAppInfo() {
        return commonDao.getTrisClientAppInfo();
    }

    @Override
    public void insertSomething(long id) {
        commonDao.insertSomething(id);
        baseService.insertSomething();
        throw new UnsupportedOperationException("111");
    }


    @Override
    public void insertSomethingElse(long id) {
        if(id == 11) {
            throw new UnsupportedOperationException("123");
        }
        commonDao.insertSomething(id);
    }

    public static void main(String[] args) {
        ICommonService iCommonService =
                AppContextFactory.getAppContext().getBean(ICommonService.class.getName(), ICommonService.class);
        iCommonService.insertSomething(11);
        //iCommonService.insertSomething(11);
    }

    public void setCommonDao(ICommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public void setBaseService(IBaseService baseService) {
        this.baseService = baseService;
    }
}
