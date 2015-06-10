package com.ai.tris.server.utils;

/**
 * Json util
 * Created by Sam on 2015/6/9.
 */
public class JsonUtil {

    /**
     * 该字符串可能转为 JSONObject 或 JSONArray
     *
     * @param string
     * @return
     */
    public static boolean mayBeJSON(String string) {
        return ((string != null) && ((("null".equals(string))
                || ((string.startsWith("[")) && (string.endsWith("]"))) || ((string
                .startsWith("{")) && (string.endsWith("}"))))));
    }

    /**
     * 该字符串可能转为JSONObject
     *
     * @param string
     * @return
     */
    public static boolean mayBeJSONObject(String string) {
        return ((string != null) && ((("null".equals(string))
                || ((string.startsWith("{")) && (string.endsWith("}"))))));
    }

    /**
     * 该字符串可能转为 JSONArray
     *
     * @param string
     * @return
     */
    public static boolean mayBeJSONArray(String string) {
        return ((string != null) && ((("null".equals(string))
                || ((string.startsWith("[")) && (string.endsWith("]"))))));
    }
}
