package com.example.miaosha.exception;

/**
 * Created by xuwencong on 2019/3/28
 */
public class CacheLockException extends Exception{

    String message;

    public CacheLockException(String message) {
        this.message = message;
    }
}
