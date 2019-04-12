package com.example.pacong.Jsoup;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwencong on 2019/4/12
 */
public class Main2 {
    /**
     * 准备抓取的目标地址，%E4%BA%92%E8%81%94%E7%BD%91 为utf-8格式的 互联网
     */
    private static String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=互联网";

    public static void main(String[] args) throws Exception {
        //链接到目标地址
        Connection connect = Jsoup.connect(url);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").timeout(6000).ignoreContentType(true).get();
        Thread.sleep(1000);
        //获取指定标签的数据
        Element elementById = document.getElementById("content_left");
        //输出文本数据
        //System.out.println(elementById.text());
        //输出html数据
        //System.out.println(elementById.html());

        //获取所有图片链接
        Elements imgtag = document.getElementsByTag("img");
        List<String> imgurlList = new ArrayList<String>();
        for (Element anImgtag : imgtag) {
            if (StringUtils.isNotEmpty(anImgtag.attr("src")) && anImgtag.attr("src").startsWith("http")) {
                System.out.println(anImgtag.attr("src"));
            }
        }
    }

}
