package com.ai.tris.server;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NA
 * Created by Sam on 2015/6/2.
 */
public class TrisServerTest {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void testTrisServer() {
        TrisServer ts = new TrisServer();
        log.info("something.." + ts.hashCode());
    }
}
