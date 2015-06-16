package com.ai.tris.server.db;


import java.util.concurrent.locks.ReentrantLock;

/**
 * Abstract id generator. Abstracts some common actions and attributes
 * from different implementations, for example, <code>lastId</code>,
 * <code>stepLength</code> etc.
 * <p/>
 * Created by Sam on 2015/6/15.
 */
public abstract class AbstractIdGenerator implements IdGenerator {
    protected String sequenceName;
    long lastId;
    int stepLength;
    String tableName;

    /**
     * Concurrent control flag. In order to put back concurrent control algorithm
     * and let different generators can work at same time, this is the reason why
     * put a reentrant lock here.
     * <p/>
     * That will be a fact that there are a lot of tables(sequences) will be
     * requested at the same time.
     */
    private ReentrantLock lock = new ReentrantLock();

    protected AbstractIdGenerator(long lastId, String seqName, int stepLength) {
        this.lastId = lastId;
        this.stepLength = stepLength;
        this.sequenceName = seqName;
        if (seqName.endsWith("$SEQ")) {
            tableName = this.sequenceName.substring(0, sequenceName.length() - 4);
        } else {
            tableName = this.sequenceName;
        }
    }

    @Override
    public long getNewId() {
        lock.lock();
        long newId;
        if (stepLength-- > 0) {
            newId = lastId++;
        } else {
            resetLastId();
            newId = getNewId();
        }
        lock.unlock();
        return newId;
    }

    protected abstract void resetLastId();

    protected void updateCursor(long lastId, int stepLength) {
        this.lastId = lastId;
        this.stepLength = stepLength;
    }
}
