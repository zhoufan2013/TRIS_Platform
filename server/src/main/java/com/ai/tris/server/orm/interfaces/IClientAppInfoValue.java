package com.ai.tris.server.orm.interfaces;

import com.ai.tris.server.orm.AbstractDataObject;

/**
 * Object bean interface mapping to table tris.client_app_info
 * Created by Sam on 2015/6/9.
 */
public interface IClientAppInfoValue {

    String S_ID = "ID";
    String S_APP_ID = "APP_ID";
    String S_SECRET_KEY = "SECRET_KEY";
    String S_STATE = "STATE";
    String S_CREATE_DATE = "CREATE_DATE";
    String S_EXPIRE_DATE = "EXPIRE_DATE";

    String S_TABLE_NAME = "CLIENT_APP_INFO";
}