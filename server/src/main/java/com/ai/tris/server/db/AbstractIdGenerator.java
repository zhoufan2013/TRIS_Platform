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

    /**
     * sequence name, this maybe a virtual concept for some kind of
     * databases, such as MySQL.
     */
    protected String sequenceName;

    /**
     * The latest id value
     */
    long lastId;

    /**
     * step size.
     */
    int stepLength;

    /**
     * table name
     */
    String tableName;

    /**
     * Concurrent control flag. In order to put back concurrent control algorithm
     * and let different generators can work at same time, this is the reason why
     * put a reentrant lock here.
     * <p/>
     * That will be a fact that there are a lot of tables(sequences) will be
     * requested at the same time.
     */
    private ReentrantLock lock = new ReentrantLock(Boolean.TRUE);

    /**
     * Constructor of Abstract class.
     * If sequence name ends with '$SEQ', after removing the last four
     * characters it exactly will be the table name.
     *
     * @param lastId     last number
     * @param seqName    sequence name
     * @param stepLength step length
     */
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

    /**
     * The key method of generator.
     *
     * @return new long value
     */
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

    /**
     * Fetch new data (last_number and step_length) from database. Due to the different
     * algorithm of different databases, so this method is defined as abstract.
     */
    protected abstract void resetLastId();


    protected void updateCursor(long lastId, int stepLength) {
        this.lastId = lastId;
        this.stepLength = stepLength;
    }
}
