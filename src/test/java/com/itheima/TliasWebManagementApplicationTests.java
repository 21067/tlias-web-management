package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//单元测试类

//@SpringBootTest
class TliasWebManagementApplicationTests {

//    @Test
    public void testUuid(){
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    /**
     * 生成JWT
     */
    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()//调用builder来构建一个jwt令牌
                //调用三个方法，来指定签名算法,自定义内容，以及jwt的有效期
                .signWith(SignatureAlgorithm.HS256, "itheima")//两个参数，一个为签名算法，一个是秘钥字符串，用来设置签名算法
                .setClaims(claims) //自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效期为1h
                .compact();//最终调用compact方法生成一个jwt令牌
        System.out.println(jwt);
    }




    /**
     * 解析JWT
     */
    //@Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")//调用setSigningKey来指定签名秘钥，再调用parse方法来解析jwt令牌
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxOTQ5NDUwMn0.FhXYTubc46iu3oZZSrtp-an1C85U72-cUpiQjZ5q5EA")
                .getBody();//拿到自定义内容
        System.out.println(claims);
    }


}
