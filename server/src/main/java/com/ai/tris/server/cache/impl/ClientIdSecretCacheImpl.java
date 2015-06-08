package com.ai.tris.server.cache.impl;

import com.ai.tris.server.cache.ICache;
import com.ai.tris.server.db.mongodb.DbConstInfo;
import com.ai.tris.server.db.mongodb.TrisMongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Cache app client info to process memory.
 * <p/>
 * Created by Sam on 2015/6/8.
 */
public class ClientIdSecretCacheImpl implements ICache {

    final String APP_ID = "appId"; // field in tris_base.client_app_info
    final String SECRET = "secret";

    public Map<String, Object> loadData() {
        Map<String, Object> retData = new HashMap<String, Object>();
        MongoDatabase mongoDatabase = TrisMongoClient.get(DbConstInfo.DbName.base);
        MongoCollection<Document> clientAppInfoDoc =
                mongoDatabase.getCollection(DbConstInfo.Collection.clientAppInfo);
        if (null != clientAppInfoDoc) {
            MongoCursor<Document> findIterable = clientAppInfoDoc.find().iterator();
            while (findIterable.hasNext()) {
                Document doc = findIterable.tryNext();
                if (null != doc) {
                    retData.put(doc.getString(APP_ID), doc.getString(SECRET));
                }
            }
        }
        return retData;
    }
}
