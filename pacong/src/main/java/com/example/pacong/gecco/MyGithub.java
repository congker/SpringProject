package com.example.pacong.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * Created by xuwencong on 2019/4/9
 */
@Gecco(matchUrl = "https://github.com/{user}/{project}", pipelines = "consolePipeline")
@Data
public class MyGithub implements HtmlBean {
    private static final long serialVersionUID = -7127412585200687225L;

    @RequestParameter("user")
    private String user;

    @RequestParameter("project")
    private String project;

    @Text
    @HtmlField(cssPath = ".repository-meta-content")
    private String title;

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(2) .social-count")
    private int star;

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(3) .social-count")
    private int fork;

    @Html
    @HtmlField(cssPath = ".entry-content")
    private String readme;

    public static void main(String[] args) {
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("com.geccocrawler.gecco.demo")
                //开始抓取的页面地址
                .start("https://github.com/xtuhcy/gecco")
                //开启几个爬虫线程
                .thread(10)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();

    }
}
