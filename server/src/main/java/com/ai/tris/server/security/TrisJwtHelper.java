package com.ai.tris.server.security;

import com.ai.tris.server.internal.com.auth0.jwt.Algorithm;
import com.ai.tris.server.internal.com.auth0.jwt.JWTSigner;

import java.util.Map;

/**
 * Tris json web token manager.
 * <p/>
 * Created by Sam on 2015/6/9.
 */
public class TrisJwtHelper {

    private static TrisJwtHelper jwtHelper = new TrisJwtHelper();
    /**
     * default algorithm HS256
     */
    private Algorithm algorithm = Algorithm.HS256;
    private JWTSigner.Options options = new JWTSigner.Options();

    private TrisJwtHelper() {
        options.setAlgorithm(this.algorithm);
    }

    public static TrisJwtHelper getInstance() {
        return jwtHelper;
    }

    /**
     * Sign claims. Secret is a key parameter. Normally it is only stored in server side.
     *
     * @param claims     some key-values
     * @param secret     secret key
     * @param expSeconds it means that token expires after expSeconds.
     * @return a signed token string
     */
    public String sign(Map<String, Object> claims, String secret, int expSeconds) {
        JWTSigner jwtSigner = new JWTSigner(secret);
        setExpireSeconds(expSeconds);
        return jwtSigner.sign(claims, this.options);
    }

    /**
     * Set token expire seconds.
     *
     * @param expSeconds expire seconds
     */
    private void setExpireSeconds(int expSeconds) {
        if (expSeconds > 0)
            this.options.setExpirySeconds(expSeconds);
    }

    /**
     * Change default algorithm to an other one.
     *
     * @param algorithm algorithm
     */
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

}
