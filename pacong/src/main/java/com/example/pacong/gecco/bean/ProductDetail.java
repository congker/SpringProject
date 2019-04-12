package com.example.pacong.gecco.bean;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

/**
 * Created by xuwencong on 2019/4/9
 */
@Gecco(matchUrl = "http://news.iresearch.cn/content/{yeary}/{month}/{code}.shtml", pipelines = {"consolePipeline", "productDetailPipeline"})
@Data
public class ProductDetail implements HtmlBean {
    private static final long serialVersionUID = -377053120283382723L;

    //    @Text
    @HtmlField(cssPath = "body > div.g-content > div.g-bd.f-mt-auto > div > div.g-mn > div > div.g-article > div.m-article")
    private String content;

    @RequestParameter
    private String code;

    @RequestParameter
    private String year;

    @RequestParameter
    private String month;

    /**
     * 标题
     */
    @Text
    @HtmlField(cssPath = "body > div.g-content > div.g-main.f-mt-auto > div > div > div.title > h1")
    private String title;

}
