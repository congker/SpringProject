package com.example.miaosha.controller;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Created by xuwencong on 2019/3/29
 */
public class MyRunnable implements Runnable {
    String watchKeys = "watchkeys";// 监视keys
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    String userInfo;

    public MyRunnable() {
    }

    public MyRunnable(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        try {
            jedis.watch(watchKeys);
            String val = jedis.get(watchKeys);
            int intVal = Integer.valueOf(val);
            if (intVal <= 100 && intVal >= 1) {
                Transaction tx = jedis.multi();// 开启事务
                // tx.incr("watchkeys");
                tx.incrBy("watchkeys", -1);

                List<Object> list = tx.exec();// 提交事务，如果此时watchkeys被改动了，则返回null

                if (list == null || list.size() == 0) {
                    String failUserInfo = "fail" + userInfo;
                    String failInfo = "用户" + failUserInfo + "抢购失败";
                    System.out.println("failinfo:" + failInfo);

                    jedis.setnx(failUserInfo, failInfo);  /* 抢购失败业务逻辑 */
                } else {
                    for (Object succ : list) {
                        String successInfo = "succ" + succ.toString() + userInfo;
                        String succInfo = "用户：" + successInfo + "抢购成功，当前抢购成功人数:" + (1 - (intVal - 100));
                        System.out.println("succinfo：" + succInfo);
                        jedis.setnx(successInfo, succInfo);   /* 抢购成功业务逻辑 */
                    }
                }

            } else {
                String failUserInfo = "kcfail" + userInfo;
                String failInfo = "用户：" + failUserInfo + "商品被抢购完毕，抢购失败";
                System.out.println(failInfo);
                jedis.setnx(failUserInfo, failInfo);
                // Thread.sleep(500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }

}
