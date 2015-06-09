package com.ai.tris.server.service.interfaces;

import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import com.ai.tris.server.orm.interfaces.IClientAppInfoValue;

import java.util.List;

/**
 *
 * Created by Vin on 2015/6/9.
 */
public interface ICommonService {

    void printCallStack();

    List<ClientAppInfoBean> getAllClientAppInfo();
}
