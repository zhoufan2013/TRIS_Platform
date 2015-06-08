package com.ai.tris.server.resource;

import com.ai.tris.server.cache.CacheFactory;
import com.ai.tris.server.cache.impl.StaticDataCacheImpl;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private transient static Logger logger = LoggerFactory.getLogger(PermissionResource.class);

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String auth(String reqData) {
        if (logger.isDebugEnabled()) {
            logger.debug("API-{}, Method-{}, request string-{}", "sing", "post", reqData);
        }
        BsonDocument bd = new BsonDocument();
        bd.put("token", new BsonString("11Aft#$FAWEF@"));
        Object signPath = CacheFactory.getCacheData(StaticDataCacheImpl.class.getName(), "signPath");
        bd.put("signPath", new BsonString(String.valueOf(signPath)));
        return bd.toJson();
    }

}
