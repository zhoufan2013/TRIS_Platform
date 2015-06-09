package com.ai.tris.server.security;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/**
 * Response builder.
 *
 * Created by Sam on 2015/6/9.
 */
public class ResponseBuilder {

    /**
     * response code, remaining to be defined.
     */
    final static String rspCode = "rspCode";
    /**
     * response content.
     */
    final static String rspInfo = "rspInfo";

    final static String infoType = "infoType";

    /**
     * response document.
     */
    private BsonDocument rspDoc = new BsonDocument();

    /**
     * build invalid response document.
     *
     * @param code
     * @param content
     * @param contentType
     * @return
     */
    public ResponseBuilder buildInvalidRsp(String code, String content, int contentType) {
        rspDoc.put(rspCode, new BsonString(code));
        rspDoc.put(rspInfo, new BsonString(content));
        rspDoc.put(infoType, new BsonInt32(contentType));
        return this;
    }

    public ResponseBuilder appendElement(String key, BsonValue value) {
        this.rspDoc.append(key, value);
        return this;
    }

    /**
     * generate json string content.
     * @return String
     */
    public String toJson() {
        return this.rspDoc.toJson();
    }

    public BsonDocument export() {
        return BsonDocument.parse(this.rspDoc.toJson());
    }
}
