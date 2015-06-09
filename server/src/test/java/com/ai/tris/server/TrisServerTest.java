package com.ai.tris.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * NA
 * Created by Sam on 2015/6/2.
 */
public class TrisServerTest {
    private Log log = LogFactory.getLog(getClass());

    @Test
    public void testTrisServer() {
        TrisServer ts = new TrisServer();
        log.info("something.." + ts.hashCode());
    }
}
