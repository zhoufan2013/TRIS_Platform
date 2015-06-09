package com.ai.tris.server.resource;

import com.ai.tris.server.cache.CacheFactory;
import com.ai.tris.server.cache.impl.ClientIdSecretCacheImpl;
import com.ai.tris.server.security.ResponseBuilder;
import com.ai.tris.server.security.TrisJwtHelper;
import com.ai.tris.server.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.json.JsonParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

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
     * <p/>
     * <p/>
     * response:
     * response body content template {rspCode:"1000", rspInfo:"this is optional", infoType: 1}
     * infoType=1 means that rspInfo only has string content.
     * <p/>
     * 1- normal string content, 2-Json object 3-Json array.
     * <p/>
     * <p/>
     * This api only obtains Json object which infoType is 2, just like this:
     * {appId:"xxxx", reqInfo:{userId:"xxxx", password:"xxxxx"}, infoType:2 }
     *
     * @param reqData json string, see the request info template
     * @return auth result
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String auth(String reqData) {
        // todo re-code later!!!!!
        if (!JsonUtil.mayBeJSON(reqData)) {
            String errorInfo = new ResponseBuilder().buildRspDocument("90001", "Invalid json content", 1).toJson();
            log.error(errorInfo);
            return errorInfo;
        }
        BsonDocument reqBsonDoc;
        try {
            reqBsonDoc = BsonDocument.parse(reqData);
        } catch (JsonParseException e) {
            String errorInfo = new ResponseBuilder().buildRspDocument("90001", "Parse json content failed", 1).toJson();
            log.error(errorInfo);
            return errorInfo;
        }
        if (log.isDebugEnabled()) {
            log.debug(String.format("API-%s, Method-%s, request string-%s", "sign", "post", reqBsonDoc));
        }

        BsonInt32 infoType = reqBsonDoc.getInt32("infoType");
        if (null == infoType || infoType.intValue() != 2) {
            return new ResponseBuilder().buildRspDocument("90001", "Only obtains json like {....,infoType:2...}", 1).toJson();
        }
        //BsonDocument reqInfo = reqBsonDoc.getDocument("reqInfo");
        String appId = reqBsonDoc.getString("appId").getValue();
        String secretKey = CacheFactory.getCacheDataString(ClientIdSecretCacheImpl.class.getName(), appId);
        if (log.isDebugEnabled()) {
            log.debug(String.format("App[%s] secret key is [%s]", appId, secretKey));
        }
        if (StringUtils.isEmpty(secretKey)) {
            return new ResponseBuilder().buildRspDocument("90002", "Invalid appId", 1).toJson();
        }

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("appId", appId);
        claims.put("signedTimestamp", System.currentTimeMillis());
        String signedToken = TrisJwtHelper.getInstance().sign(claims, secretKey, 86400);

        BsonDocument signResult = new ResponseBuilder()
                .buildRspDocument("2000", StringUtils.EMPTY, 2)
                .appendRspInfo("token", new BsonString(signedToken))
                .export();
        if (log.isDebugEnabled()) {
            log.debug(String.format("Signed result %s", signResult));
        }
        return signResult.toJson();
    }
}
