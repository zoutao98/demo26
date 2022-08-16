package com.tes.demo26.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class JwtUtils {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;// 默认15分钟
    // 私钥
    private static final String TOKEN_SECRET = "zoutaoPrivateKey";

    public static String createJwtToken(String claimInfo) {
        try {
            // 设置过期时间
            long now = System.currentTimeMillis();
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("user", claimInfo)
                    .withJWTId(UUID.randomUUID().toString())
                    .withExpiresAt(new Date(now + +EXPIRE_TIME))
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parseJwtToken(String token) {
        try {
            return JWT.decode(token).getClaim("user").asString();
        } catch (JWTVerificationException e) {
            // 无效的签名/声明
            e.printStackTrace();

            return null;
        }
    }

}
