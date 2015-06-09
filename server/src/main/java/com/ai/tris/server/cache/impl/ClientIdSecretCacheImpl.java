package com.ai.tris.server.cache.impl;

import com.ai.tris.server.AppContextFactory;
import com.ai.tris.server.cache.ICache;
import com.ai.tris.server.orm.impl.ClientAppInfoBean;
import com.ai.tris.server.orm.interfaces.IClientAppInfoValue;
import com.ai.tris.server.service.interfaces.ICommonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cache app client info to process memory.
 * <p/>
 * Created by Sam on 2015/6/8.
 */
public class ClientIdSecretCacheImpl implements ICache {

    public Map<String, Object> loadData() {
        Map<String, Object> retData = new HashMap<String, Object>();

        ICommonService iCommonService =
                AppContextFactory.getAppContext().getBean(ICommonService.class.getName(), ICommonService.class);
        List<ClientAppInfoBean> clientAppInfoBeans = iCommonService.getAllClientAppInfo();
        if (null != clientAppInfoBeans && clientAppInfoBeans.size() > 0) {
            for (ClientAppInfoBean clientAppInfoBean : clientAppInfoBeans) {
                retData.put(clientAppInfoBean.getString(IClientAppInfoValue.S_APP_ID),
                        clientAppInfoBean.get(IClientAppInfoValue.S_SECRET_KEY));
            }
        }
        return retData;
    }
}
