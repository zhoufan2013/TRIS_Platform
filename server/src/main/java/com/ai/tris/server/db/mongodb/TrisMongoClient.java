/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ai.tris.server.db.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mongo database client
 * <p/>
 * Created by Sam on 2014/11/14.
 */
public class TrisMongoClient {

    private final static String SERVER_PROP_NAME = "server.properties";
    private final static String SERVER_INFO_KEY = "db.mongo.server";

    private static String clientUri = null;

    /**
     * Mongodb client pool
     */
    private final static Map<String, Object> clientPool = new ConcurrentHashMap<String, Object>();


    private static com.mongodb.MongoClient borrowClient(String clientKey) {
        return (com.mongodb.MongoClient) clientPool.get(clientKey);
    }

    /**
     * Create mongo database access client. If Client uri initializes failed or just is empty,
     * give up the creation.
     *
     * @return mongo database client.
     */
    private static MongoClient createMongoClient() {
        if (StringUtils.isEmpty(clientUri)) {
            throw new RuntimeException("Give up trying to create a client using empty uri.");
        }
        return new MongoClient(new MongoClientURI(clientUri));
    }

    /**
     * get database instance handler
     *
     * @param dbName database name
     * @return database handler
     */
    public static MongoDatabase get(String dbName) {
        return borrowClient("_mc").getDatabase(dbName);
    }

    /**
     * Pool mongo client.
     *
     * @param clientKey client name
     * @param client    client instance
     */
    private static void poolMongoClient(String clientKey, MongoClient client) {
        clientPool.put(clientKey, client);
    }

    /**
     * Load mongo server information from server.properties file. After calling this
     * method, Ip, port, user, db and pwd will be got.
     *
     * @throws IOException
     */
    private static void loadServerProp() throws IOException {
        Properties prop = new Properties();
        prop.load(TrisMongoClient.class.getClassLoader().getResourceAsStream(SERVER_PROP_NAME));
        clientUri = prop.getProperty(SERVER_INFO_KEY);
        if (StringUtils.isEmpty(clientUri)) {
            throw new RuntimeException(
                    String.format("Load mongo auth info failed, please check %s in file %s",
                            SERVER_INFO_KEY, SERVER_PROP_NAME));
        }
    }

    static {
        try {
            loadServerProp();
        } catch (IOException ioe) {
            throw new RuntimeException(
                    String.format("Load mongo server information from file (%s) failed.", SERVER_PROP_NAME));
        }
        poolMongoClient("_mc", createMongoClient());
    }
}
