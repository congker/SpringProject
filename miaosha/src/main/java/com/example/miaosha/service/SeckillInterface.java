package com.example.miaosha.service;

import com.example.miaosha.aop.CacheLock;
import com.example.miaosha.aop.LockedObject;

/**
 * Created by xuwencong on 2019/3/28
 */
public interface SeckillInterface {
    //cacheLock注解可能产生并发的方法
    @CacheLock(lockedPrefix="TEST_PREFIX")
    void secKill(String userID,@LockedObject Long commidityID);
    //最简单的秒杀方法，参数是用户ID和商品ID。可能有多个线程争抢一个商品，所以商品ID加上LockedObj注解

}
