package com.example.pacong.geccoBlog;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * Created by xuwencong on 2019/4/10
 */
public class Main {
    public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("com.example.pacong.geccoBlog")
                .start("http://www.cnblogs.com/boychen/p/7226831.html")
                .thread(5)
                .interval(5)
                .mobile(false)
                .run();
    }
}
