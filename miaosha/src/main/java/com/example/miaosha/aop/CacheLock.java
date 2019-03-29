package com.example.miaosha.aop;

import java.lang.annotation.*;

/**
 * Created by xuwencong on 2019/3/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    String lockedPrefix() default "";//redis 锁key的前缀
    long timeOut() default 2000;//轮询锁的时间
    int expireTime() default 1000;//key在redis里存在的时间，1000S
}
