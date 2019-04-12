package com.example.pacong.Jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by xuwencong on 2019/4/12
 */
public class Main {
    private static String URL = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=互联网";

    public static void main(String[] args) throws Exception {
        //链接到目标地址
        Connection connect = Jsoup.connect(URL);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(6000).ignoreContentType(true).get();
        Thread.sleep(1000);
        //获取指定标签的数据
        Element elementById = document.getElementById("content_left");
        //输出文本数据
        System.out.println(elementById.text());
        //输出html数据
        System.out.println(elementById.html());
    }
}
