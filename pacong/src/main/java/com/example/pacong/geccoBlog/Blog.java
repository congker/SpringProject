package com.example.pacong.geccoBlog;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.SpiderBean;
import lombok.Data;

/**
 * Created by xuwencong on 2019/4/10
 */
@Gecco(matchUrl="http://www.cnblogs.com/boychen/p/7226831.html",pipelines="blogPipelines")
@Data
class Blog implements SpiderBean {
    /**
     * 向指定URL发送GET方法的请求
     */
    @Request
    private HttpRequest request;

    /**
     * 抓去这个路径下所有的内容
     */
    @HtmlField(cssPath = "body div#cnblogs_post_body")
    private String content;
}
