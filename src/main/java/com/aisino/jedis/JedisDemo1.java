package com.aisino.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author wuxiang
 * @date 2021/12/1 2:13 下午
 */
public class JedisDemo1 {

    public static void main(String[] args) {
        //创建jedis对象
        Jedis jedis=new Jedis("localhost",6379);
        String ping = jedis.ping();
        System.out.println(ping);
        System.out.println("哈哈哈");
    }
}
