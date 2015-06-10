package com.ai.tris.server.security;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/**
 * Response builder.
 * <p/>
 * Created by Sam on 2015/6/9.
 */
public class ResponseBuilder {

    /**
     * response code, remaining to be defined.
     */
    final static String RSP_CODE = "rspCode";
    /**
     * response content.
     */
    final static String RSP_INFO = "rspInfo";

    final static String INFO_TYPE = "infoType";

    final static int CONTENT_TYPE_STRING = 1;
    final static int CONTENT_TYPE_DOCUMENT = 2;

    /**
     * response document.
     */
    private BsonDocument rspDoc = new BsonDocument();

    /**
     * build invalid response document.
     *
     * @param code        response status
     * @param content     response information
     * @param contentType response body information type
     * @return ResponseBuilder itself
     */
    public ResponseBuilder buildRspDocument(int code, String content, int contentType) {
        if (contentType == CONTENT_TYPE_DOCUMENT) {
            return buildRspDocument(code, new BsonDocument(), contentType);
        }
        rspDoc.clear();
        rspDoc.put(RSP_CODE, new BsonInt32(code));
        rspDoc.put(RSP_INFO, new BsonString(content));
        rspDoc.put(INFO_TYPE, new BsonInt32(contentType));
        return this;
    }

    public ResponseBuilder buildRspDocument(int code, BsonDocument content, int contentType) {
        rspDoc.clear();
        rspDoc.put(RSP_CODE, new BsonInt32(code));
        rspDoc.put(INFO_TYPE, new BsonInt32(contentType));
        rspDoc.put(RSP_INFO, content);
        return this;
    }


    public ResponseBuilder appendElement(String key, BsonValue value) {
        this.rspDoc.append(key, value);
        return this;
    }

    /**
     * Append BsonValue to RspInfo. If it doesn't exist, create it and then do append work.
     *
     * @param key   Bson value name
     * @param value BsonValue
     * @return ResponseBuilder
     */
    public ResponseBuilder appendRspInfo(String key, BsonValue value) {
        BsonDocument rspInfo = rspDoc.getDocument(RSP_INFO);
        if (null == rspInfo) {
            rspInfo = new BsonDocument();
            rspDoc.append(RSP_INFO, rspInfo);
        }
        rspInfo.append(key, value);
        return this;
    }


    /**
     * generate json string content.
     *
     * @return String
     */
    public String toJson() {
        return this.rspDoc.toJson();
    }

    public BsonDocument export() {
        return BsonDocument.parse(this.rspDoc.toJson());
    }
}
