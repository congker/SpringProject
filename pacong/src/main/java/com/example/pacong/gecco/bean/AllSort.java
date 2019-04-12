package com.example.pacong.gecco.bean;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.util.List;

/**
 * Created by xuwencong on 2019/4/9
 */
@Gecco(matchUrl = "http://news.iresearch.cn/", pipelines = {"consolePipeline", "allSortPipeline"})
@Data
public class AllSort implements HtmlBean {
    private static final long serialVersionUID = 665662335318691818L;

    @Request
    private HttpRequest request;

    // 移动互联网
    @HtmlField(cssPath = "#tab-list > div:nth-child(1)")
    private List<Category> mobileInternet;

    // 电子商务
    @HtmlField(cssPath = "#tab-list > div:nth-child(2)")
    private List<Category> electric;

    // 互联网
    @HtmlField(cssPath = "#tab-list > div:nth-child(3)")
    private List<Category> internet;

    // 网络营销
    @HtmlField(cssPath = "#tab-list > div:nth-child(4)")
    private List<Category> netMarket;

    // 网络游戏
    @HtmlField(cssPath = "#tab-list > div:nth-child(5)")
    private List<Category> netGame;


}
