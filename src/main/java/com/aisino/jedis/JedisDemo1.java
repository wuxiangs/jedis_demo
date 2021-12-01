package com.aisino.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

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
    }


    @Test
    public void testStr(){
        Jedis jedis=new Jedis("localhost",6379);
//        jedis.set("name","吴祥");
//        System.out.println(jedis.get("name"));
//        jedis.mset("age","20","address","上海");
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void testList(){
        Jedis jedis=new Jedis("localhost",6379);
        jedis.lpush("k1","张三","李四","王五");
        List<String> k1 = jedis.lrange("k1", 0, -1);
        for (int i = 0; i < k1.size(); i++) {
            System.out.println(k1.get(i));
        }
    }

    @Test
    public void testSet(){
        Jedis jedis=new Jedis("localhost",6379);
        jedis.sadd("k2","lili","kk","ll");
        Set<String> k2 = jedis.smembers("k2");
        for (String s : k2) {
            System.out.println(s);
        }
//        Iterator<String> iterator = k2.iterator();
//        while (iterator.hasNext()) {
//            String next =  iterator.next();
//            System.out.println(next);
//        }
    }

    @Test
    public void testHash(){
        Jedis jedis=new Jedis("localhost",6379);
        Map<String,String> map=new HashMap<>();
        map.put("k1","lili");
        map.put("k2","kk");
        jedis.hset("k3",map);
        String hget = jedis.hget("k3", "k1");
        System.out.println(hget);

    }

    @Test
    public void testZset(){
        Jedis jedis=new Jedis("localhost",6379);
        jedis.zadd("china",100d,"shanghai");
        Set<String> china = jedis.zrange("china", 0, -1);
        for (String s : china) {
            System.out.println(s);
        }
    }
}
