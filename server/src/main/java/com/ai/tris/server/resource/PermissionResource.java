package com.ai.tris.server.resource;

import com.ai.tris.server.cache.CacheFactory;
import com.ai.tris.server.cache.impl.ClientIdSecretCacheImpl;
import com.ai.tris.server.cache.impl.StaticDataCacheImpl;
import com.ai.tris.server.security.ResponseBuilder;
import com.ai.tris.server.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Sign in resource module
 * <p/>
 * Created by Sam on 2015/6/2.
 */
@Path("sign")
public class PermissionResource {

    private transient static Log log = LogFactory.getLog(PermissionResource.class);

    /**
     * request:
     * body content template:
     * <code>{appId:"xxxx", reqInfo:"String or JsonObject", infoType: 1} </code> or
     * <code>{appId:"xxxx", reqInfo:{userId:"xxxx", password:"xxxxx"}, infoType:2 }</code>
     * <p/>
     *
     *
     * response:
     * response body content template {rspCode:"1000", rspInfo:"this is optional", infoType: 1}
     * infoType=1 means that rspInfo only has string content.
     * <p/>
     * 1- normal string content, 2-Json object content 3-Json array.
     *
     *
     * This api only obtains Json object which infoType is 2, just like this:
     * {appId:"xxxx", reqInfo:{userId:"xxxx", password:"xxxxx"}, infoType:2 }
     *
     * @param reqData json string, see the request info template
     * @return auth result
     */
    @POST    @Consumes("application/json")    @Produces("application/json")
    public String auth(String reqData) {
        // todo re-code later!!!!!
        if (!JsonUtil.mayBeJSON(reqData)) {
            String errorInfo = new ResponseBuilder().buildInvalidRsp("90001", "Invalid json content.", 1).toJson();
            log.error(errorInfo);
            return errorInfo;
        }
        BsonDocument reqBsonDoc = BsonDocument.parse(reqData);
        if (log.isDebugEnabled()) {
            log.debug(String.format("API-%s, Method-%s, request string-%s", "sign", "post", reqBsonDoc));
        }

        BsonInt32 infoType = reqBsonDoc.getInt32("infoType");
        if(null == infoType || infoType.intValue() != 2) {
            return new ResponseBuilder().buildInvalidRsp("90001", "Only obtains json like {....,infoType:2...}.", 1).toJson();
        }
        //BsonDocument reqInfo = reqBsonDoc.getDocument("reqInfo");
        String appId = reqBsonDoc.getString("appId").getValue();
        String secretKey = CacheFactory.getCacheDataString(ClientIdSecretCacheImpl.class.getName(), appId);
        if (log.isDebugEnabled()) {
            log.debug(String.format("App[%s] secret key is [%s]", appId, secretKey));
        }
        if(StringUtils.isEmpty(secretKey)) {
            return new ResponseBuilder().buildInvalidRsp("90002", "Invalid appId.", 1).toJson();
        }
        BsonDocument bd = new BsonDocument();
        bd.put("token", new BsonString("11Aft#$FAWEF@"));
        Object signPath = CacheFactory.getCacheData(StaticDataCacheImpl.class.getName(), "signPath");
        bd.put("signPath", new BsonString(String.valueOf(signPath)));
        return bd.toJson();
    }
}
