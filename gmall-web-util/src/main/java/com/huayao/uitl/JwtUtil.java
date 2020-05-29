package com.huayao.uitl;

import io.jsonwebtoken.*;

import java.util.Map;

/**
 * @author huayao
 */
public class JwtUtil {


    /**
     * 生成token
     * key值，用户信息，盐值
     * 公共部分（算法）  ，私有部分(用户信息(id，名称))，  签名部分（颜值（ip）和key）
     * @param key
     * @param param
     * @param salt
     * @return
     */
    public static String encode(String key, Map<String,Object>param,String salt){
        if (salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder = jwtBuilder.setClaims(param);
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解密token
     * token key salt
     * @param token
     * @param key
     * @param salt
     * @return
     */
    public static Map<String,Object> decode(String token,String key,String salt){
        Claims claims = null;
        if (salt!=null){
            key+=salt;
        }
        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch (JwtException e){
            return null;
        }
        return claims;
    }

}


















