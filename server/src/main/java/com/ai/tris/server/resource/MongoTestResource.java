package com.ai.tris.server.resource;

import com.ai.tris.server.db.mongodb.TrisMongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Mongo database test resource
 * <p/>
 * Created by Sam on 2015/6/4.
 */
@Path("mongo")
public class MongoTestResource {
    private transient static Logger log = LoggerFactory.getLogger(MongoTestResource.class);

    @Path("/get_something")
    @GET
    @Produces("application/json")
    public String getSomething() {
        MongoDatabase trisBase = TrisMongoClient.get("tris_base");
/*        ListCollectionsIterable<Document> iterable = trisBase.listCollections();
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            log.info(cursor.tryNext().toJson());
        }*/
        MongoCollection<Document> cacheData = trisBase.getCollection("cache_data");
        FindIterable<Document> findIterable = cacheData.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Document retJson = new Document();
        if (mongoCursor.hasNext()) {
            retJson = mongoCursor.tryNext();
        }
        if (log.isDebugEnabled()) {
            log.debug("return data {}", retJson.toJson());
        }
        return retJson.toJson();
    }
}
