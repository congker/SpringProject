package com.example.miaosha.aop;

import java.lang.annotation.*;

/**
 * Created by xuwencong on 2019/3/28
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {

}
