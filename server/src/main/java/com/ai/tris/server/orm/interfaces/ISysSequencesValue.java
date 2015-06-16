package com.ai.tris.server.orm.interfaces;

/**
 * Object bean interface mapping to table tris.sys_sequences.
 * <p/>
 * Created by Sam on 2015/6/16.
 */
public interface ISysSequencesValue {

    String S_SEQUENCE_NAME = "SEQUENCE_NAME";
    String S_START_BY = "START_BY";
    String S_INCREMENT_BY = "INCREMENT_BY";
    String S_LAST_NUMBER = "LAST_NUMBER";
    String S_JVM_STEP_BY = "JVM_STEP_BY";

    String S_TABLE_NAME = "SYS_SEQUENCES";
}