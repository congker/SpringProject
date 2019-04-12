package com.example.pacong.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;

/**
 * Created by xuwencong on 2019/4/9
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("start Crawler-->");

        HttpGetRequest startUrl = new HttpGetRequest("http://news.iresearch.cn/");
        startUrl.setCharset("GBK");
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("com.example.pacong.gecco")
                //开始抓取的页面地址
                .start(startUrl)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .run();
    }
}
