package com.ai.tris.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Tris Server holds server instance, and it is built implementing Jersey.
 * <p/>
 * Created by Sam on 2015/6/2.
 */
public class TrisServer {
    /*slf4j log*/
    private static transient Logger log = LoggerFactory.getLogger(TrisServer.class);
    final static String PARAM_LIST = "PORT";
    //private static Map<String, String> MAIN_PARAMS = new HashMap<String, String>();

    /**
     * Start a process.
     *
     * @param args input parameters
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            if (log.isErrorEnabled()) {
                log.error("Wrong input parameters. See the input params: [{}]", PARAM_LIST);
            }
        }
    }

    /**
     * Parse input parameters of main function.
     *
     * @param args input params
     * @return param key-value pair
     */
    Map<String, String> parseMainInput(String[] args) {
        Map<String, String> mainInput = new HashMap<String, String>();
        if (null == args || args.length < 1) {
            return mainInput;
        }
        for (int i = 0; i < args.length; ) {
            mainInput.put(args[i++], args[i++]);
        }
        return mainInput;
    }

    /**
     * Param args can't be null.
     *
     * @param args params array
     */
    void validMainInput(String[] args) {
        if(args.length % 2 != 0) {
            throw new RuntimeException("Invalid Input Parameters. Please pass in key-value pair(s)");
        }
    }

}
