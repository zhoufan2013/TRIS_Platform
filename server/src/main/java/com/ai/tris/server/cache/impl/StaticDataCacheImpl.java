package com.ai.tris.server.cache.impl;

import com.ai.tris.server.cache.ICache;
import com.ai.tris.server.db.mongodb.DbConstInfo;
import com.ai.tris.server.db.mongodb.TrisMongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * load static data to cache.
 * <p/>
 * Created by Sam on 2015/6/8.
 */
public class StaticDataCacheImpl implements ICache {

    final static String COLLECTION = "static_data";

    public Map<String, Object> loadData() {
        Map<String, Object> retData = new HashMap<String, Object>();
        MongoDatabase mongoDatabase = TrisMongoClient.get(DbConstInfo.DbName.base);
        MongoCollection<Document> collection = mongoDatabase.getCollection(COLLECTION);
        if (null != collection) {
            BsonDocument filter = new BsonDocument();
            filter.put("dataType", new BsonString("static"));
            filter.put("state", new BsonString("U"));
            FindIterable<Document> findIterable = collection.find(filter);
            if (null != findIterable) {
                MongoCursor<Document> mongoCursor = findIterable.iterator();
                while (mongoCursor.hasNext()) {
                    Document document = mongoCursor.tryNext();
                    retData.put(document.getString("dataCode"), document.getString("dataValue"));
                }
            }
            return retData;
        }
        return retData;
    }
}
