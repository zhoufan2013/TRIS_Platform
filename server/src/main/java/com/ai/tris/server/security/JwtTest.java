package com.ai.tris.server.security;

import com.ai.tris.server.internal.com.auth0.jwt.JWTSigner;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Sam on 2015/6/7.
 */
public class JwtTest {

    public static void main(String[] args) {
        JWTSigner jwtSigner = new JWTSigner("TrisWeb_PWD-123");
        Map<String, Object> claim = new HashMap<String, Object>();
        claim.put("AppId", "TrisWeb#001");
        System.out.println(jwtSigner.sign(claim));
    }
}
