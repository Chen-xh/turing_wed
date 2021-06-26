package com.turing.website.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author CHEN
 * @date 2020/3/1 17:02
 */

@Component
public class JWTUtil {


    // 过期时间一小时
    private static final long EXPIRE_TIME = 60*60*1000;
    private static final Logger PLOG = LoggerFactory.getLogger(JWTUtil.class);


    /**
     * 校验token是否正确
     * @param token TOKEN
     * @param secret 用户的密码
     * @return boolean
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            PLOG.error("JWT >> " + e);
            return false;
        }
    }

    /**
     * 无需解密直接获得token中的用户名
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            PLOG.error("JWT >> " + e);
            return null;
        }
    }

    public static String getType(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("type").asString();
        } catch (JWTDecodeException e) {
            PLOG.error("JWT >> " + e);
            return null;
        }
    }

    /**
     * 生成签名，并设置过期时间
     * @param username 用户名
     * @param secret 用户的密码
     * @return token
     */
    public static String sign(String username, String secret,String type) {
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("type", type)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            PLOG.error("JWT >> " + e);
            return null;
        }
    }


}
