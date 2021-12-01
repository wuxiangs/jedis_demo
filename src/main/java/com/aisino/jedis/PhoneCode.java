package com.aisino.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author wuxiang
 * @date 2021/12/1 3:30 下午
 */
public class PhoneCode {


    public static void main(String[] args) {
       //模拟验证码发送
        verifyCode("15955429191");
    }

    /**
     * 生成六位的验证码
     * @return
     */
    public static String getCode(){
        Random random=new Random();
        String code="";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code+=rand;
        }
        return code;
    }

    /**
     * 每个手机每天只能发送三次，验证码放到redis中,设置过期时间2分钟
     */

    public static void verifyCode(String phone){
        Jedis jedis=new Jedis("localhost",6379);

        //每个手机每天发送三次
        String phone1 = jedis.get("phone");
        if (phone1==null){
            //设置发送次数为1
            jedis.setex(phone+":count",24*60*60L,"1");
        }else if(Integer.parseInt(phone1) <= 2){
            //发送次数加1
            jedis.incr(phone);
        }else{
            //发送次数操作三次了
            System.out.println("发送次数操作三次了");
            jedis.close();
            return;
        }
        //验证码放到redis中,且设置过期时间
        jedis.setex(phone+":code",120L,getCode());
    }

    /**
     * 验证码校验
     */
    public void getRedisCode(String phone,String code){
        Jedis jedis=new Jedis("localhost",6379);
        String s = jedis.get(phone + ":code");
        if (s.equals(code)){
            System.out.println("登陆成功");
        }else{
            System.out.println("验证码错误,登陆失败");
            jedis.close();
        }

    }
}

