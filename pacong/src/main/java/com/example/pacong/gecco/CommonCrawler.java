package com.example.pacong.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * Created by xuwencong on 2019/4/9
 */
@Data
public class CommonCrawler implements HtmlBean {
    @Request
    private HttpRequest request;


    @HtmlField(cssPath = "body")
    private String body;

    public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("com.example.pacong.gecco")
                .start("https://www.baidu.com/")
                .interval(2000)
                .start();
    }
}
