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

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mongo database client
 * <p/>
 * Created by Sam on 2014/11/14.
 */
public class TrisMongoClient {

    private final static String HOST = "10.1.231.4";
    private final static int PORT = 27017;

    private final static String[][] credentialMatrix = new String[][]{
            {"tris", "tris_base", "tris-123"}
    };

    /**
     * Mongodb client pool
     */
    private final static Map<String, Object> clientPool = new ConcurrentHashMap<String, Object>();


    private static com.mongodb.MongoClient borrowOneClient(String clientKey) {
        return (com.mongodb.MongoClient) clientPool.get(clientKey);
    }

    /**
     * create mongodb connection credential.
     *
     * @return credential
     */
    private static List<MongoCredential> createCredentials() {
        List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
        MongoCredential credential;
        for (String[] _credential : credentialMatrix) {
            credential = MongoCredential.createCredential(_credential[0], _credential[1], _credential[2].toCharArray());
            credentialList.add(credential);
        }
        return credentialList;
    }

    private static com.mongodb.MongoClient createMongoClient() {
        ServerAddress address = new ServerAddress(HOST, PORT);
        return new com.mongodb.MongoClient(address, createCredentials());
    }

    /**
     * get database instance handler
     *
     * @param dbName database name
     * @return database handler
     */
    public static MongoDatabase get(String dbName) {
        return borrowOneClient("_mc").getDatabase(dbName);
    }

    private static void poolMongoClient(String clientKey, com.mongodb.MongoClient mc) {
        clientPool.put(clientKey, mc);
    }

    static {
        poolMongoClient("_mc", createMongoClient());
    }
}
