package com.ai.tris.server.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * File Util
 * Created by Sam on 2015/6/2.
 */
public class FileUtil {

    private static Log logger = LogFactory.getLog(FileUtil.class);

    /**
     * Read file content. This kind of file are packaged to jar file, or set to
     * Java classpath.
     *
     * @param cpPath classpath path
     * @return the content of file
     */
    public static String readFileContent(String cpPath) {
        try {
            InputStream is = FileUtil.class.getResourceAsStream(cpPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            is.close();
            br.close();
            return sb.toString();
        } catch (IOException ioe) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("Failed to read file [%s]", cpPath), ioe);
            }
            return StringUtils.EMPTY;
        }
    }
}
