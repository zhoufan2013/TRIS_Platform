package com.ai.tris.server.dao.interfaces;

import com.ai.tris.server.orm.impl.ClientAppInfoBean;

import java.util.List;

/**
 * Common Database Access Object.
 * <p/>
 * Created by Sam on 2015/6/9.
 */
public interface ICommonDao {
    void querySomething();

    /**
     * get all client app information from tris.client_app_info
     */
    List<ClientAppInfoBean> getTrisClientAppInfo();

    void insertSomething(long id);


}